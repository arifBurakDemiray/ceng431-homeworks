/**
 * 
 */
package storage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import exception.ItemNotFoundException;



public abstract class Container<T> implements IContainer<T> {
	
	private List<T> container;
	
	public Container(){
		container = new ArrayList<T>();
	}
	
	public List<T> getContainer()
	{
		return container;
	}
	
	public boolean add(T item){
		if(this.isExist(item))
			return false;
		
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
	
	public List<T> getList() {
		return container;
		
	}
	
	public abstract T getById(String id) throws ItemNotFoundException;
		
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
	
	
	protected T search(T item) {
		T obj = null;
		for(T itm : container) {
			if(itm.equals(item)){
				obj = itm;
				break;
			}
		}
		return obj;
	}
	
	/**
	@author AMCIKLAR BURAYA BAKIN BURDA LİSTE DÖNDĞREK"
	*/
	public String toString()
	{
		String string = "";
		for(T item: this.container)
		{
			string+=item.toString();
		}
		return string;
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return container.iterator();
	} 
	
	public T getItem(T item) {
		return search(item);
	}
	
}
