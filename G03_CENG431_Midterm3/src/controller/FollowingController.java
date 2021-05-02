package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ButtonState;
import model.UpdatedList;
import model.User;
import observation.Observable;
import observation.Observer;
import service.FollowService;
import view.FollowingView;

/**
* This class handles following view actions
*/
public class FollowingController extends Consumable {

	private User model;
	private Observer view;
	private FollowService service;

	public FollowingController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		service = new FollowService();
		updateList(); //update views list
		((FollowingView) this.view).addUnfollowButtonListener(new UnfollowButtonListener());
		((FollowingView) this.view).addBackButtonListener(new BackButtonListener());
		
	}

	//this class handles unfollow button requests
	//if user presses unfollows selected user
	class UnfollowButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = ((FollowingView) view).getSelectedUser();
			service.unfollowUser(name,model);
			updateList();
		}
	}

	//this class handles back button requests
	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify(ButtonState.BACK_BUTTON);
			model.removeObserver(view);
		}
	}
	
	//this function updates list of followings
	private void updateList()
	{	
		UpdatedList updatedList = new UpdatedList(service.setFollowList(model.getFollowings()));
		model.setAndNotify(updatedList);
	}

}
