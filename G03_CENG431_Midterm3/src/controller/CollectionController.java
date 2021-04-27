package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Collection;
import model.User;
import observation.Observable;
import observation.Observer;
import view.user.CollectionView;
import view.user.PopupView;

public class CollectionController {

	private User model;
	private Observer view;

	public CollectionController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		((CollectionView) this.view).addBackButtonListener(new BackButtonListener());
		((CollectionView) this.view).addSelectCollectionListener(new SelectCollectionListener());
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify("back");
			model.removeObserver(view);
		}
	}

	class SelectCollectionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			String selectedCollectionName = ((CollectionView) view).getSelectedCollection();
			Collection collection = null;
			try {
				collection = model.getCollections().getByName(selectedCollectionName);				
				PopupView pv = new PopupView(collection);
				PopupController pc = new PopupController(collection,pv);
			} catch (ItemNotFoundException | NotSupportedException e1) {
			}
			
			// model.setAndNotify("select");
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

	class WindowListener extends WindowAdapter {
		Observer viewAdapter;
		Observable modelAdapter;
		
		public WindowListener(Observable collection, Observer pv) {
			super();
			this.viewAdapter = pv;
			this.modelAdapter = collection;
		
		}
		
		@Override
		public void windowClosed(WindowEvent arg0) {
			// observer is deleted
			modelAdapter.removeObserver(viewAdapter);
		}

	}

}
