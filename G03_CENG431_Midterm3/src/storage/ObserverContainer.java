package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import observation.Observer;

public class ObserverContainer extends Container<Observer> {

	@Override
	public Observer getById(String id) throws ItemNotFoundException, NotSupportedException {
		throw new NotSupportedException("Observer container does not supports getById() function");
	}

	@Override
	public Observer getByName(String name) throws ItemNotFoundException, NotSupportedException {
		throw new NotSupportedException("Observer container does not supports getById() function");
	}

}
