package service;


import fileio.DatabaseResult;
import fileio.UserRepository;
import model.User;

public class AuthService {

	private UserRepository userRepository;

	public AuthService() {
		this.userRepository = new UserRepository();
	}

	public User login(String userName, String password) {
		DatabaseResult result = userRepository.getUserByName(userName);
		User found = (User) result.getObject();
		if (found != null) { // if user found, look his password
			final String psw = ((User)found).getPassword();
			if (!psw.equals(password)) {
				found = null;
			}
		}
		return found;
	}

}
