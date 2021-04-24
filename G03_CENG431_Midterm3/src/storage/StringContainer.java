package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;

public class StringContainer extends Container<String> {

	@Override
	public String getById(String id) throws ItemNotFoundException, NotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getByName(String name) throws ItemNotFoundException, NotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		String string = "";
		for (String item : this.getContainer()) {
			if (item != null)
				string += item+",";
		}
		if (string.endsWith(",")) { // if ends with , ignore it
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}

}
