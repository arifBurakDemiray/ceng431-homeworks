package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.User;
import observation.Observable;
import observation.Observer;
import view.user.CollectionView;

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
			((CollectionView)view).getSelectedCollection();			
			model.setAndNotify("select");
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
