package controller;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.User;
import storage.IContainer;

public class LoginController {
	
	
	public LoginController() {
		
	}
	
	
	public class LoginListener extends ActionListener{
		
	}
	
	public User login(String userName, String password, IContainer<User> users){
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
				
			}
		} 

	}
	
	
	

}
