package service;

import javax.swing.DefaultListModel;
import fileio.UserRepository;
import model.User;

public class DiscoverUserService {

	public DiscoverUserService() {
	}

	public DefaultListModel<String> setUsersList(User model) {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		UserRepository userRepository = new UserRepository();
		for (User user : userRepository.getUsers().getContainer()) {
			String temp = ((User) model).getFollowings().getItem(user.getUserName());
			if ((temp == null) && !user.equals(model)) {
				listModel.addElement(user.getUserName());
			}
		}
		return listModel;
	}
}
