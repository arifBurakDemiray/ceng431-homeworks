package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.FileController;
import model.User;
import observation.Observable;
import observation.Observer;
import view.user.DiscoverUsersView;

public class DiscoverUsersController {

	private User model;
	private Observer view;

	public DiscoverUsersController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		((DiscoverUsersView) this.view).addFollowButtonListener(new FollowButtonListener());
		((DiscoverUsersView) this.view).addBackButtonListener(new BackButtonListener());
	}

	class FollowButtonListener implements ActionListener {
		private String name;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			name = ((DiscoverUsersView) view).getSelectedUser();
			followUser(name);
			try {
				User otherUser = FileController.getByUserName(name);
				addFollower(otherUser);
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

	public void addFollower(User otherUser) {
		otherUser.getFollowers().add(model.getUserName());
		model.setAndNotify("addUser");
	}

	public void followUser(String name) {
		model.getFollowings().add(name);
		model.setAndNotify("followUser");
	}

}
