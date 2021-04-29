package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fileio.DatabaseResult;
import fileio.UserRepository;
import model.User;
import observation.Observable;
import observation.Observer;
import view.DiscoverUsersView;

public class DiscoverUsersController {

	private User model;
	private Observer view;
	private UserRepository userRepository;
	public DiscoverUsersController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		userRepository = new UserRepository();
		((DiscoverUsersView) this.view).addFollowButtonListener(new FollowButtonListener());
		((DiscoverUsersView) this.view).addBackButtonListener(new BackButtonListener());
	}

	class FollowButtonListener implements ActionListener {
		private String name;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			name = ((DiscoverUsersView) view).getSelectedUser();
			followUser(name);
			DatabaseResult databaseResult = userRepository.getUserByName(name);
			User otherUser = (User) databaseResult.getObject();
			if(otherUser!=null)
			{
				addFollower(otherUser);
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

	public void addFollower(User otherUser) {
		otherUser.getFollowers().add(model.getUserName());;
		userRepository.saveChanges();
	}

	public void followUser(String name) {
		model.getFollowings().add(name);
		model.setAndNotify("followUser");
		userRepository.saveChanges();
	}

}
