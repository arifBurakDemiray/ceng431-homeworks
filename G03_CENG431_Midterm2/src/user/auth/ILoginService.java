package user.auth;

import exception.WrongCredentialException;
import storage.IContainer;
import user.User;

/**
 * This class makes login operation for the system
 */
public interface ILoginService {

	/**
	 * This function logins a user if credentials matches
	 * 
	 * @param userName of the user
	 * @param password of the user
	 * @param users    repository
	 * @returns user if user exists and given password matches
	 * @throws WrongCredentialException for given wrong password
	 */
	public User login(String userName, String password, IContainer<User> users) throws WrongCredentialException;

}
