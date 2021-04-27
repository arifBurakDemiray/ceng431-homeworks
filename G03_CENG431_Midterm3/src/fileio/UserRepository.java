package fileio;

import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import model.User;

public class UserRepository {

	public UserRepository() {

	}

	public DatabaseResult saveChanges() {
		String message = "";
		try {
			BaseRepository.saveChanges();
		} catch (FileFormatException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(null, message);
	}

	public DatabaseResult getUserByName(String name) {
		final IContainer<User> users = BaseRepository.users();
		String message = "";
		Object result = null;
		try {
			result = users.getByName(name);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	public final IContainer<User> getUsers() {
		return BaseRepository.users();
	}
}
