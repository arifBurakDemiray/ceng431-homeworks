package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ButtonState;
import model.UpdatedList;
import model.User;
import observation.Observable;
import observation.Observer;
import service.FollowService;
import view.FollowerView;

public class FollowerController {

	private User model;
	private Observer view;
	private FollowService service;

	public FollowerController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);		
		service = new FollowService();
		updateList();
		((FollowerView) this.view).addBackButtonListener(new BackButtonListener());
	}


	class BackButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify(ButtonState.BACK_BUTTON);
			model.removeObserver(view);
		}		
	}
	
	private void updateList()
	{	
		UpdatedList updatedList = new UpdatedList(service.setFollowList(model.getFollowers()));
		model.setAndNotify(updatedList);
	}

}
