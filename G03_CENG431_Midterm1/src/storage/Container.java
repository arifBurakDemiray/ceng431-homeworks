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
		if (this.isExist(item)) //if item exit return false
			return false;
		boolean result = container.add(item); //otherwise add it and return
		return result; 
	}

	public Collection<T> getContainer() {
		return this.container;
	}

	//gets an item by searching
	public T getItem(T item) {
		T result = search(item); 
		return result;
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

	//gets item by its id if has
	public abstract T getById(String id) throws ItemNotFoundException, NotSupportedException;

	//gets item by its name if has
	public abstract T getByName(String name) throws ItemNotFoundException, NotSupportedException;

	/**
	 * The function returns the result of that : the given item is in the container
	 * or not.
	 *
	 * @param item given item.
	 * @return true/false
	 */
	private boolean isExist(T item) {
		boolean boolValue = false;

		for (T itm : container) {
			if (itm.equals(item)) {//if item equals 
				boolValue = true; //make result true
				break; //and break the loop
			}
		}
		return boolValue;
	}

	/**
	 * The function tries to remove the given item from container. If process is
	 * Successful, it returns the removed item. not.
	 *
	 * @param item given item.
	 * @return item
	 */
	public T remove(T item) throws ItemNotFoundException {
		boolean result = container.remove(item);
		if (!result) { //if not removed
			throw new ItemNotFoundException();
		} else { //else return it
			return item;
		}
	}

	/**
	 * The function searches the given item in the container to find equivalent
	 * object which is in the container.
	 *
	 * @param item given item.
	 * @return T item is in the container which is equal to the given item.
	 * 			else null for not found
	 */
	protected T search(T item) {
		T obj = null;
		if (!isEmpty()) { //if container is not empty
			for (T itm : container) { //iterate through container
				if (itm.equals(item)) { //if equals mean found
					obj = itm;
					break; //break loop
				}
			}
		}
		return obj; //return value
	}

	/**
	 * The function converts all container items to string.
	 *
	 * @return string value of a container.
	 */
	public String toString() {
		String string = "";
		for (T item : this.container) {
			if (item != null)
				string += item.toString() + ",";
		}
		if (string.endsWith(",")) {	//if ends with , ignore it
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}

}
