/**
 * 
 */
package storage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import exception.ItemNotFoundException;
import exception.NotSupportedException;

public abstract class Container<T> implements IContainer<T> {

	private List<T> container;

	public Container() {
		container = new ArrayList<T>();
	}

	public boolean add(T item) {
		if (this.isExist(item))
			return false;

		return container.add(item);
	}

	public abstract T getById(String id) throws ItemNotFoundException, NotSupportedException;

	public List<T> getContainer() {
		return container;
	}

	public T getItem(T item) {
		return search(item);
	}

	public int getLength() {
		return container.size();
	}

	public List<T> getList() {
		return container;

	}

	public boolean isEmpty() {
		return this.container.isEmpty();
	}

	private boolean isExist(T item) {
		boolean boolValue = false;

		for (T itm : container) {
			if (itm.equals(item)) {
				boolValue = true;
				break;
			}
		}
		return boolValue;
	}

	public Iterator<T> iterator() {
		return container.iterator();
	}

	public T remove(T item) throws ItemNotFoundException {
		if (!container.remove(item)) {
			throw new ItemNotFoundException();
		} else {
			return item;
		}

	}

	public T removeById(int id) throws ItemNotFoundException {
		if (!container.remove(null)) {
			throw new ItemNotFoundException();
		} else {
			return null;
		}

	}

	protected T search(T item) {
		T obj = null;
		for (T itm : container) {
			if (itm.equals(item)) {
				obj = itm;
				break;
			}
		}
		return obj;
	}

	public String toString() {
		String string = "";
		for (T item : this.container) {
			string += item.toString() + ",";
		}
		if (string.endsWith(",")) {
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}

}
