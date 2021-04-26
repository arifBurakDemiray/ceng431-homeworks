package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.FileController;
import model.User;
import observation.Observable;
import observation.Observer;
import view.user.FollowingView;

public class FollowingController {

	private User model;
	private Observer view;

	public FollowingController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		((FollowingView) this.view).addUnfollowButtonListener(new UnfollowButtonListener());
		((FollowingView) this.view).addBackButtonListener(new BackButtonListener());
	}

	class UnfollowButtonListener implements ActionListener {
		private String name;

		@Override
		public void actionPerformed(ActionEvent e) {
			name = ((FollowingView) view).getSelectedUser();
			unfollowUser(name);
			try {
				User otherUser = FileController.getByUserName(name);
				removeFollower(otherUser, model.getUserName());
			} catch (ItemNotFoundException | NotSupportedException e1) {
			}
		}
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify("back");
			model.removeObserver(view);
		}
	}

	public void removeFollower(User otherUser, String name) {
		try {
			otherUser.getFollowers().remove(name);
		} catch (ItemNotFoundException e) {
		}
		model.setAndNotify("removeUser");
	}


	public void unfollowUser(String name) {
		try {
			model.getFollowings().remove(name);
		} catch (ItemNotFoundException e) {
		}
		model.setAndNotify("unfollowUser");
	}

}
