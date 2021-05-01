package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.ButtonState;
import fileio.DatabaseResult;
import fileio.UserRepository;
import model.UpdatedList;
import model.User;
import observation.Observable;
import observation.Observer;
import service.DiscoverUserService;
import view.DiscoverUsersView;

public class DiscoverUsersController {

	private User model;
	private Observer view;
	private UserRepository userRepository;
	private DiscoverUserService service;
	public DiscoverUsersController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		userRepository = new UserRepository();
		service = new DiscoverUserService();
		((DiscoverUsersView) this.view).addFollowButtonListener(new FollowButtonListener());
		((DiscoverUsersView) this.view).addBackButtonListener(new BackButtonListener());
		updateList();
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
			model.setAndNotify(ButtonState.BACK_BUTTON);
			model.removeObserver(view);
		}
	}

	public void addFollower(User otherUser) {
		otherUser.getFollowers().add(model.getUserName());;
		userRepository.saveChanges();
	}

	public void followUser(String name) {
		model.getFollowings().add(name);
		updateList();
		userRepository.saveChanges();
	}
	
	private void updateList()
	{	
		UpdatedList updatedList = new UpdatedList(service.setUsersList(model));
		model.setAndNotify(updatedList);
	}

}
