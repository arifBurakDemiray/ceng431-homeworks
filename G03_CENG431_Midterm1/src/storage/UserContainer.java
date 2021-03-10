package storage;
import java.util.Iterator;

import exception.ItemNotFoundException;
import team.Team;
import user.User;
public class UserContainer extends Container<User> {

	@Override
	public User getById(String id) throws ItemNotFoundException {
		User returnedUser = null;
		for(User user : getContainer()) {
			if(user.getId().equals(id)){
				
				returnedUser = user;
			}
		}
		if(returnedUser == null)
		{
			throw new ItemNotFoundException();
		}
		else
		{
			return returnedUser;
		}
	}

	
	
	

	
}
