package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ButtonState;
import model.UpdatedList;
import model.User;
import observation.Observable;
import observation.Observer;
import service.DiscoverUserService;
import view.DiscoverUsersView;

/**
 * This class handles follow request from DiscoverUsersView
 */
public class DiscoverUsersController extends Consumable {

	private User model;
	private Observer view;
	private DiscoverUserService service;

	public DiscoverUsersController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		service = new DiscoverUserService(); // add listeners to view
		((DiscoverUsersView) this.view).addFollowButtonListener(new FollowButtonListener());
		((DiscoverUsersView) this.view).addBackButtonListener(new BackButtonListener());
		updateList(); // update available followable users
	}

	// This class handles follow requests
	class FollowButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) { // get selected user
			String name = ((DiscoverUsersView) view).getSelectedUser();
			service.followUser(name, model); // and follow user
			updateList(); // update list
		}
	}

	// This class handles back button requests
	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify(ButtonState.BACK_BUTTON); // if pressed go back
			model.removeObserver(view); // and remove observer from model
		}
	}

	private void updateList() {
		// get updated list
		UpdatedList updatedList = new UpdatedList(service.setUsersList(model));
		model.setAndNotify(updatedList); // and notify views to update list
	}

}
