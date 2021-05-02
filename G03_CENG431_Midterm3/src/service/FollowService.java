package service;

import javax.swing.DefaultListModel;

import exception.ItemNotFoundException;
import fileio.DatabaseResult;
import fileio.UserRepository;
import model.User;
import storage.IContainer;

/**
 * This class handles following operations and also communicates with repository
 */
public class FollowService {

	private final UserRepository userRepository;

	public FollowService() {
		userRepository = new UserRepository();
	}

	/**
	 * This function creates updated user list for the user
	 * 
	 * @param followList followers list or followings list
	 * @return updated user list for JList
	 */
	public DefaultListModel<String> setFollowList(IContainer<String> followList) {

		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String user : followList) {
			listModel.addElement(user);
		}
		return listModel;

	}

	/**
	 * This function unfollows user
	 * 
	 * @param userName that is going to be unfollowed
	 * @param model    that is going to unfollow
	 */
	public void unfollowUser(String userName, User model) {
		// check userName that in the database or not
		DatabaseResult databaseResult = userRepository.getUserByName(userName);
		User otherUser = (User) databaseResult.getObject();
		if (otherUser != null) { // if found
			unfollowUser(model, userName); // unfollow User
			removeFollower(otherUser, model.getUserName()); // remove model from other user followers
			userRepository.saveChanges(); // save changes
		}

	}

	private void removeFollower(User otherUser, String name) {
		try {
			otherUser.getFollowers().remove(name); // remove from other followers
		} catch (ItemNotFoundException e) {
		}
	}

	private void unfollowUser(User model, String name) {
		try {
			model.getFollowings().remove(name); // remove from followings
		} catch (ItemNotFoundException e) {
		}
	}

}
