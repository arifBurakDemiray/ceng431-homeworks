/**
 * 
 */
package storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import exception.ItemNotFoundException;
import exception.NotSupportedException;

public abstract class Container<T> implements IContainer<T> {

	private Collection<T> container; // it holds objects of given type.

	/**
	 * The constructor to create a container which holds the objects of given type.
	 */
	public Container() {
		container = new ArrayList<T>();
	}

	public boolean add(T item) {
		if (this.isExist(item))
			return false;

		return container.add(item);
	}
	
	public Collection<T> getContainer() {
		return this.container;
	}

	public T getItem(T item) {
		return search(item);
	}

	public int getLength() {
		return container.size();
	}

	public boolean isEmpty() {
		return this.container.isEmpty();
	}
	
	public Iterator<T> iterator() {
		return container.iterator();
	}
	
	public abstract T getById(String id) throws ItemNotFoundException, NotSupportedException;

	public abstract T getByName(String name) throws ItemNotFoundException, NotSupportedException;

	/**
	 * The function returns the result of that : the given item is in the container or
	 * not.
	 *
	 * @param item given item.
	 * @return true/false
	 */
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

	/**
	 * The function tries to remove the given item from container. If process is successfull, it returns the removed item.
	 * not.
	 *
	 * @param item given item.
	 * @return item
	 */
	public T remove(T item) throws ItemNotFoundException {
		if (!container.remove(item)) {
			throw new ItemNotFoundException();
		} else {
			return item;
		}
	}

	/**
	 * The function searches the given item in the container to find equilavent
	 * object which is in the container.
	 *
	 * @param item given item.
	 * @return T item is in the container which is equal to the given item.
	 */
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

	/**
	 * The function prints the all container items.
	 *
	 * @return string to print.
	 */
	public String toString() {
		String string = "";
		for (T item : this.container) {
			if(item!=null)
				string += item.toString() + ",";
		}
		if (string.endsWith(",")) {
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}

}
