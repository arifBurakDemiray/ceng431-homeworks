/**
 * 
 */
package storage;

import java.util.Iterator;
import java.util.List;

import exception.NotSupportedException;
import exception.ItemNotFoundException;


public interface IContainer<T> extends Iterable<T>{
	/**
	 * This function adds an elements to the container
	 * @param item which is going to be added to the container
	 */
	public boolean add(T item);
	/**
	 * 	
	 * This function returns an element by its id
	 * @param id searched item's id
	 * @return s searched item if not found throws 
	 * @throws ItemNotFoundException if not found by id it throws that exception
	 */
	public T getById(String id) throws ItemNotFoundException,NotSupportedException;
	
	
	public T getItem(T item);
	
	/**
	 * This function returns the length of the collection
	 * @return s the length of the collection
	 */
	public int getLength();
	
	public List<T> getList();
	
	public boolean isEmpty();
	
	public Iterator<T> iterator();
	
	/**
	 * This function removes given element
	 * @param item that is going to be deleted
	 * @return s the deleted item
	 * @throws ItemNotFoundException if not found that item throw and ItemNotFound exception
	 */
	public T remove(T item) throws ItemNotFoundException;
	
	/**
	 * This function removes element by its id
	 * @param id the id of element that is going to be deleted
	 * @return s deleted item
	 * @throws ItemNotFoundException if not found
	 */
	public T removeById(int id) throws ItemNotFoundException;
	
}
