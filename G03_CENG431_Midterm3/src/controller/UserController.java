package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.FileController;
import model.Collection;
import model.Outfit;
import model.User;
import observation.Observable;
import observation.Observer;
import view.DiscoverUsersView;
import view.FollowingView;

public class UserController {

	/*private User model;
	private Observer view;

	public UserController(Observable model, Observer view) {
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
				User otherUser = FileController.users().getByName(name);
				removeFollower(otherUser, model.getUserName());
			} catch (ItemNotFoundException | NotSupportedException e1) {
			}

		}
	}

	class FollowButtonListener implements ActionListener {
		private String name;

		@Override
		public void actionPerformed(ActionEvent e) {
			name = ((DiscoverUsersView) view).getSelectedUser();
			try {
				User otherUser = FileController.users().getByName(name);
				followUser(otherUser);
			} catch (ItemNotFoundException | NotSupportedException e1) {
			}

		}
	}

	class BackButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify("back");
		}
		
	}

	public void createCollection() {
		Collection collection = null; // returnedCollection
		model.getCollections().add(collection);
	}

	public void addOutfit(Collection collection, Outfit outfit) {
		boolean isAdded = collection.getOutfits().add(outfit);

	}

	public void removeOutfit(Collection collection, Outfit outfit) {

		try {
			collection.getOutfits().remove(outfit);

		} catch (ItemNotFoundException e) {

		}

	}

	public void followUser(User otherUser) {
		model.getFollowings().add(otherUser.getUserName());
		otherUser.getFollowers().add(model.getUserName());
		model.setAndNotify("follow");
	}

	public void removeFollower(User otherUser, String name) {
		try {
			otherUser.getFollowers().remove(name);
		} catch (ItemNotFoundException e) {
		}
		model.setAndNotify("removeUser");
	}

	public void addFollowing(String name) {
	}

	public void unfollowUser(String name) {
		try {
			model.getFollowings().remove(name);
		} catch (ItemNotFoundException e) {
		}
		model.setAndNotify("unfollowUser");
	}*/

}
