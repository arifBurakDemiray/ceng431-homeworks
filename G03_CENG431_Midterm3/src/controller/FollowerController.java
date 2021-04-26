package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;
import observation.Observable;
import observation.Observer;
import view.user.FollowerView;

public class FollowerController {

	private User model;
	private Observer view;

	public FollowerController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);		
		((FollowerView) this.view).addBackButtonListener(new BackButtonListener());
	}


	class BackButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify("back");
			model.removeObserver(view);
		}		
	}

}
