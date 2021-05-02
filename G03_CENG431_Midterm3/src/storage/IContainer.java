package storage;

import java.util.Collection;
import java.util.Iterator;

import exception.NotSupportedException;
import exception.ItemNotFoundException;

public interface IContainer<T> extends Iterable<T> {

	/**
	 * The function adds the given object to the container if it is not added
	 * before.
	 *
	 * @param item given item to add to the container.
	 * @return true if added, otherwise false
	 */
	public boolean add(T item);

	/**
	 * The abstract function tries to return the item of given id if it is in the
	 * container.
	 *
	 * @param id id of given item.
	 * @return T object
	 * @throws ItemNotFoundException
	 * @throws NotSupportedException if item does not have a getId function
	 *
	 */
	public T getById(String id) throws ItemNotFoundException, NotSupportedException;

	/**
	 * This is an abstract function and it tries to get an item by its name.
	 * 
	 * @param name of the item
	 * @returns item if found
	 * @throws ItemNotFoundException if not found throws not found exception
	 * @throws NotSupportedException if item does not have a getName function
	 */
	public T getByName(String name) throws ItemNotFoundException, NotSupportedException;

	/**
	 * The function returns the container
	 *
	 * @return Container
	 */
	public Collection<T> getContainer();

	/**
	 * The function returns the object of the given item if given item is in the
	 * container, invoking search() function in the body
	 *
	 * @param item given item.
	 * @return T item
	 */
	public T getItem(T item);

	/**
	 * The function returns the length of the container
	 *
	 * @return container size
	 */
	public int getLength();

	/**
	 * The function returns the result of that container is empty or not.
	 *
	 * @return true/false
	 */
	public boolean isEmpty();

	/**
	 * The function returns the iterator of the container
	 *
	 * @return Iterator of the container
	 */
	public Iterator<T> iterator();

	/**
	 * The function tries to remove the given item if the given item is in the
	 * container.
	 *
	 * @param item given item.
	 * @return T removed item.
	 * @throws ItemNotFoundException
	 */
	public T remove(T item) throws ItemNotFoundException;

	/**
	 * The function converts all container items to string.
	 *
	 * @return string value of a container.
	 */
	public String toString();

	/**
	 * This function converts a IContainer object to its array value
	 * 
	 * @return array value of IContainer
	 */
	public T[] toArray();

}
