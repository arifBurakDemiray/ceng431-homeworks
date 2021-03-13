package storage;


import exception.ItemNotFoundException;
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

	@Override
	public User getByName(String name) throws ItemNotFoundException {
		User found = null;
		for(User user : this.getContainer()) {
			if(user.getName().equals(name)){
				found = user;
				break;
			}
		}
		if(found == null)
		{
			throw new ItemNotFoundException();
		}
		else
		{
			return found;
		}
	}
}

	
	
	

	

