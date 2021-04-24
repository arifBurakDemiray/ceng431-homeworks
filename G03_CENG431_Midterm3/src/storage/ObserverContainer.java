package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import observation.Observer;

public class ObserverContainer extends Container<Observer> {

	@Override
	public Observer getById(String id) throws ItemNotFoundException, NotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observer getByName(String name) throws ItemNotFoundException, NotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
