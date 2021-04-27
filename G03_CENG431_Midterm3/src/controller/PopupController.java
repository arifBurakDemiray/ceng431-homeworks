package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.DatabaseResult;
import fileio.OutfitRepository;
import model.Collection;
import model.Outfit;
import observation.Observable;
import observation.Observer;
import view.user.PopupView;

public class PopupController {

	private Observable model;
	private Observer view;
	private OutfitRepository outfitRepository;

	public PopupController(Observable model, Observer view) {
		this.model = (Collection) model;
		this.view = view;
		model.addObserver(view);
		outfitRepository = new OutfitRepository();
		((PopupView) this.view).addRemoveButtonListener(new RemoveButtonListener());
		((PopupView) this.view).addAddButtonListener(new AddButtonListener());
		((PopupView) this.view).addWindowExitListener(new WindowExitListener());
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify("back");
			model.removeObserver(view);
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
			} catch (ItemNotFoundException | NotSupportedException e1) {
			}		
		}
	}
	
	class AddButtonListener implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedOutfitId = ((PopupView) view).getSelectedOutfitToAdd();
			DatabaseResult databaseResult = outfitRepository.getOutfitById(selectedOutfitId);			
			Outfit selectedOutfit = (Outfit)databaseResult.getObject();
			
			if(selectedOutfit!=null)
			{
				addOutfit(selectedOutfit);	
			}
			else
			{
				//message basılacak
			}	
		}
	}
	
	public void addOutfit(Outfit outfit) {
		((Collection)model).getOutfits().add(outfit);
		model.setAndNotify("updateList");
		outfitRepository.saveChanges();
	}

	public void removeOutfit(Outfit outfit) throws ItemNotFoundException {
		((Collection)model).getOutfits().remove(outfit);
		model.setAndNotify("updateList");
		outfitRepository.saveChanges();
	}


	class WindowExitListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			model.setAndNotify("Close");
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			model.setAndNotify("Close");
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			model.setAndNotify("Close");
			
		}
		
	}
				



	

}
