package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exception.ItemNotFoundException;
import fileio.DatabaseResult;
import fileio.UserRepository;
import model.User;
import observation.Observable;
import observation.Observer;
import view.user.FollowingView;

public class FollowingController {

	private User model;
	private Observer view;
	private UserRepository userRepository;

	public FollowingController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		
		userRepository = new UserRepository();
		((FollowingView) this.view).addUnfollowButtonListener(new UnfollowButtonListener());
		((FollowingView) this.view).addBackButtonListener(new BackButtonListener());
	}

	class UnfollowButtonListener implements ActionListener {
		private String name;

		@Override
		public void actionPerformed(ActionEvent e) {
			name = ((FollowingView) view).getSelectedUser();
			unfollowUser(name);
			DatabaseResult databaseResult = userRepository.getUserByName(name);
			User otherUser = (User)databaseResult.getObject();
			if(otherUser!=null)
			{
				removeFollower(otherUser, model.getUserName());
				userRepository.saveChanges();
			}
			else
			{
				//message basılacak
			}
		}
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify("back");
			model.removeObserver(view);
			userRepository.saveChanges();
		}
	}

	public void removeFollower(User otherUser, String name) {
		try {
			otherUser.getFollowers().remove(name);
		} catch (ItemNotFoundException e) {
		}
		model.setAndNotify("removeUser");
		userRepository.saveChanges();
	}


	public void unfollowUser(String name) {
		try {
			model.getFollowings().remove(name);
		} catch (ItemNotFoundException e) {
		}
		model.setAndNotify("unfollowUser");
		userRepository.saveChanges();
	}

}
