package service;

import javax.swing.DefaultListModel;

import fileio.DatabaseResult;
import fileio.UserRepository;
import model.User;

public class DiscoverUserService {

	private final UserRepository users; // users repository

	public DiscoverUserService() {
		users = new UserRepository();
	}

	/**
	 * This class takes a user and gives available followable users
	 * 
	 * @param model that is user for waiting to follow available users
	 * @return available users to follow
	 */
	public DefaultListModel<String> setUsersList(User model) {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (User user : users.getUsers()) { // get system users
			String temp = ((User) model).getFollowings().getItem(user.getUserName());
			if ((temp == null) && !user.equals(model)) { // if model not in user's followers and not equal
				listModel.addElement(user.getUserName());
			}
		}
		return listModel;
	}

	/**
	 * This function follows a user for model
	 * 
	 * @param userName selected from JList
	 * @param model    that pressed follow user button
	 */
	public void followUser(String userName, User model) {
		DatabaseResult databaseResult = users.getUserByName(userName);
		User otherUser = (User) databaseResult.getObject();
		if (otherUser != null) { // if selected user exists
			followUser(model, userName); // follow user
			addFollower(otherUser, model); // and add model to followed user's followers
			users.saveChanges(); // save changes
		}
	}

	private void followUser(User user, String userName) {
		user.getFollowings().add(userName); // add userName to followings
	}

	private void addFollower(User otherUser, User user) {
		otherUser.getFollowers().add(user.getUserName()); // add user to otherUser's followers
	}
}
