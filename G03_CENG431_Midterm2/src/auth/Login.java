package auth;


import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.WrongCredentialException;
import storage.IContainer;
import user.User;

public class Login {

	public Login(){}
	
	public User login(String userName, String password,IContainer<User> users) throws WrongCredentialException, ItemNotFoundException, NotSupportedException{
		User found = users.getByName(userName);
		String psw = found.getPassword();
		if(psw.equals(password))
		{
			return found;
		}
		else
		{
			throw new WrongCredentialException("You mistyped the password");
		}
	} 
}