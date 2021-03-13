package storage;

import exception.NotSupportedException;

public class IdContainer extends Container<String>{

	@Override
	public String getById(String id) throws NotSupportedException {
		throw new NotSupportedException("src.storage.IdContainer.getById() function is not supported for IdContainer.");
	}

	@Override
	public String getByName(String name) throws NotSupportedException {
		throw new NotSupportedException("src.storage.IdContainer.getByName() function is not supported for IdContainer.");
	}

	

	


}
