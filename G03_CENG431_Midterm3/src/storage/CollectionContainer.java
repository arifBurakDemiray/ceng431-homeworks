package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Collection;

public class CollectionContainer extends Container<Collection> {

	@Override
	public Collection getById(String id) throws NotSupportedException {
		throw new NotSupportedException("Collection container does not supports getById() function");
	}

	@Override
	public Collection getByName(String name) throws ItemNotFoundException {
		Collection found = null;
		for (Collection collection : this.getContainer()) {
			if (collection.equals(name)) {
				found = collection;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no collection has name " + name);
		} else {
			return found;
		}
	}

	@Override
	// modified to write in a xml file
	public String toString() {
		return "<collections>\n" + super.toString() + "\t\t</collections>";
	}

}
