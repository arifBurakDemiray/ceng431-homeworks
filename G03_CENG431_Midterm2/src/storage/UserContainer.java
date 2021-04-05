package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import user.User;

public class UserContainer extends Container<User> {

	/**
	 * The function search the given id belongs to the any user or not. If given id
	 * belongs to a user, it returns the user
	 * 
	 * @param id given user id
	 * @return User whose id is the same with given id.
	 * @throws ItemNotFoundException
	 */
	@Override
	public User getById(String id) throws NotSupportedException {
		throw new NotSupportedException("src.storage.UserContainer.getById() function is not supported for IdContainer.");
	}

	/**
	 * The function search the given name belongs to the any user or not. If given
	 * name belongs to a user, it returns the user
	 * 
	 * @param name given user name
	 * @return User whose name is the same with given name.
	 * @throws ItemNotFoundException
	 */
	@Override
	public User getByName(String name) throws ItemNotFoundException {
		User found = null;
		for (User user : this.getContainer()) {
			if (user.equals(name)) {
				found = user;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no user has name " + name);
		} else {
			return found;
		}
	}

}
