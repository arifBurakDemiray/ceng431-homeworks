package controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.FileController;
import model.Collection;
import model.Outfit;
import observation.Observable;
import observation.Observer;
import view.user.PopupView;

public class PopupController {

	private Observable model;
	private Observer view;

	public PopupController(Observable model, Observer view) {
		this.model = (Collection) model;
		this.view = view;
		model.addObserver(view);
		((PopupView) this.view).addRemoveButtonListener(new RemoveButtonListener());
		((PopupView) this.view).addAddButtonListener(new AddButtonListener());
		((PopupView) this.view).addWindowExitListener(new ClosePopupListener());
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify("back");
			model.removeObserver(view);
		}
	}
	
	class ClosePopupListener implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			PopupView temp = (PopupView)view;
			Point location = e.getLocationOnScreen();
			Rectangle rect = temp.getBounds();
			boolean xBoundPass = location.x <= rect.x || location.x >= rect.x + rect.width;
			boolean yBoundPass = location.y <= rect.y || location.y >= rect.y + rect.height;
			
			System.out.println(location.x+"-"+rect.x+"-"+rect.width);
			System.out.println(location.y +"-"+ rect.y +"-"+ location.y +"-"+ rect.y +"-"+ rect.height);
			System.out.println(xBoundPass + "- "+ yBoundPass);
			if(xBoundPass && yBoundPass){
				System.out.println(xBoundPass + "- "+ yBoundPass);
				model.setAndNotify("Close");
			}
		
		}
		
	}
	
	class RemoveButtonListener implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedOutfitId = ((PopupView) view).getSelectedOutfitToRemove();
			
			Outfit outfit = null;
			try {
				outfit = ((Collection)model).getOutfits().getById(selectedOutfitId);	
				removeOutfit(outfit);	
				System.out.println("removed");
			} catch (ItemNotFoundException | NotSupportedException e1) {
			}		
		}
	}
	
	class AddButtonListener implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedOutfitId = ((PopupView) view).getSelectedOutfitToAdd();
			
			Outfit outfit = null;
			try {
				outfit = FileController.outfits().getById(selectedOutfitId);	
				addOutfit(outfit);			
			} catch (ItemNotFoundException | NotSupportedException e1) {
			}		
		}
	}
	
	public void addOutfit(Outfit outfit) {
		((Collection)model).getOutfits().add(outfit);
		model.setAndNotify("updateList");
	}

	public void removeOutfit(Outfit outfit) throws ItemNotFoundException {
		((Collection)model).getOutfits().remove(outfit);
		System.out.println("aaa");
		model.setAndNotify("updateList");
	}

				

	class SelectOutfitListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}
	

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	

}
