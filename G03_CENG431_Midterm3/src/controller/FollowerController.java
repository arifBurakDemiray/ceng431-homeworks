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

/**
 * This class is responsible for showing followers of the model
 */
public class FollowerController extends Consumable {

	private User model;
	private Observer view;
	private FollowService service;

	public FollowerController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		service = new FollowService();
		updateList(); // update followers for initial show
		// add back button listener
		((FollowerView) this.view).addBackButtonListener(new BackButtonListener());
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify(ButtonState.BACK_BUTTON); // if back button pressed
			model.removeObserver(view);
		}
	}

	// update list for follower view
	private void updateList() {
		UpdatedList updatedList = new UpdatedList(service.setFollowList(model.getFollowers()));
		model.setAndNotify(updatedList);
	}

}
