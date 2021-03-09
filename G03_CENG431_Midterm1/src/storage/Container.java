/**
 * 
 */
package storage;

import java.util.ArrayList;
import java.util.List;

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
	
	public boolean add(T item) throws UserExistException{
		if(this.isExist(item))
			throw new UserExistException();
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
	
	public T geyById(int id) throws ItemNotFoundException {
		return null;
	}
	
	public int getLength() {
		return container.size();
	}
	
	private boolean isExist(T item) {
		boolean boolValue = true;
		for(T itm : container) {
			if(itm.equals(item)){
				boolValue = false;
				break;
			}
		}
		return boolValue;
	}
}
