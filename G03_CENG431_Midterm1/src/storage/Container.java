/**
 * 
 */
package storage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exception.ItemExistException;
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
	
	
	/**
	@author AMCIKLAR BURAYA BAKIN BURDA LİSTE DÖNDĞREK"
	*/
	public String toString()
	{
		for(T item: this.container)
		{
			System.out.println(item.toString());
		}
		return "adas";
	}
	
	@Override
	public Iterator<T> getIterator() {
		// TODO Auto-generated method stub
		return container.iterator();
	} 
	
}
