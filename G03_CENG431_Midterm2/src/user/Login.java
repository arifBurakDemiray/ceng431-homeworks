package user;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.WrongCredentialException;
import storage.IContainer;
import user.auth.ILoginService;

/**
 * 
 * This class handles login operation
 *
 */
public class Login implements ILoginService {

	/**
	 * contructor of login class
	 */
	public Login() {
	}

	public User login(String userName, String password, IContainer<User> users) throws WrongCredentialException {
		User found = null; // init found null
		try {// try to get user by name
			found = users.getByName(userName);
		} catch (ItemNotFoundException | NotSupportedException e) {
		}
		if (found != null) { // if user found, look his password
			final String psw = found.getPassword();
			if (psw.equals(password)) {// if matches return user
				return found;
			} else {// Throw for password not matches exception
				throw new WrongCredentialException("You mistyped the password");
			}
		} else// throw for there is no user that named by given userName
			throw new WrongCredentialException("There is no user has user name " + userName);

	}
}
