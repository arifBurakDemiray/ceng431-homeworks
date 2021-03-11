package storage;

import exception.NotSupportedException;

public class IdContainer extends Container<String>{

	@Override
	public String getById(String id) throws NotSupportedException {
		throw new NotSupportedException("src.storage.IdContainer.getById() function is not supported for IdContainer.");
	}

	

	


}
