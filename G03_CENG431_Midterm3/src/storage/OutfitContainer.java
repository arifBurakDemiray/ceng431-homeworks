package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Outfit;

public class OutfitContainer extends Container<Outfit> {

	@Override
	public Outfit getById(String id) throws ItemNotFoundException {
		Outfit found = null;
		for (Outfit outfit : this.getContainer()) {
			if (outfit.equals(id)) {
				found = outfit;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no outfit has id " + id);
		} else {
			return found;
		}
	}

	@Override
	public Outfit getByName(String name) throws NotSupportedException {
		throw new NotSupportedException("Outfit container does not supports getById() function");
	}

	@Override
	//modified to write in a json file
	public String toString() {
		String string = "{";
		for (Outfit item : this.getContainer()) {
			if (item != null)
				string += item.toString() + ",";
		}
		if (string.endsWith(",")) { // if ends with , ignore it
			string = string.substring(0, string.length() - 1);
		}
		return string+"}";
	}

}
