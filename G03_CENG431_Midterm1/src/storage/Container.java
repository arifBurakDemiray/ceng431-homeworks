/**
 * 
 */
package storage;

import java.util.ArrayList;
import java.util.List;

import exception.ItemExistException;
import exception.ItemNotFoundException;
import exception.UserExistException;
import user.User;

/**
 * @author burak
 *
 */
public abstract class Container<T> implements IContainer<T> {
	
	private List<T> container;
	
	public Container(){
		container = new ArrayList<T>();
	}
	
	public List<T> getContainer()
	{
		return container;
	}
	
	public boolean add(T item) throws ItemExistException{
		if(this.isExist(item))
			throw new ItemExistException();
		
		return container.add(item);
	}
	
	public T removeById(int id) throws ItemNotFoundException {
		if(!container.remove(null)) {
			throw new ItemNotFoundException();
		}
		else {
			return null;
		}
		
	}
	
	public T remove(T item) throws ItemNotFoundException{
		return item;
		
	}
	
	public T geyById(String id) throws ItemNotFoundException {
		return null;
	}
		
	public int getLength() {
		return container.size();
	}
	
	private boolean isExist(T item) {
		boolean boolValue = false;
		
		for(T itm : container) {
			if(itm.equals(item)){
				boolValue = true;
				break;
			}
		}
		return boolValue;
	}
}
