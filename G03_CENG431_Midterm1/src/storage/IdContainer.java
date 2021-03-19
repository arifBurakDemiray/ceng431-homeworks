package storage;

import exception.NotSupportedException;

public class IdContainer extends Container<String> {

	/**
	 * The function of the abstract function returns an error about not supporting
	 * that process
	 * 
	 * @param id given id
	 * @throws NotSupportedException
	 */
	@Override
	public String getById(String id) throws NotSupportedException {
		throw new NotSupportedException("src.storage.IdContainer.getById() function is not supported for IdContainer.");
	}

	/**
	 * The function of the abstract function returns an error about not supporting
	 * that process
	 * 
	 * @param id given id
	 * @throws NotSupportedException
	 */
	@Override
	public String getByName(String name) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.IdContainer.getByName() function is not supported for IdContainer.");
	}

}
