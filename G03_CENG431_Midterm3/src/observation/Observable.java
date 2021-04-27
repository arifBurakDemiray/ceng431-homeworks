package observation;

import exception.ItemNotFoundException;
import storage.IContainer;
import storage.ObserverContainer;

public class Observable {

	private IContainer<Observer> observers;
	private boolean changed = false;

	public Observable(){
		this.observers = new ObserverContainer();
	}

	public void addObserver(Observer observer)
	{
		this.observers.add(observer);
	}
	public void removeObserver(Observer observer)
	{
		try {
			this.observers.remove(observer);
		} catch (ItemNotFoundException e) {

		}	
	}
	public void notifyObservers(Object args)
	{
		if(isChanged())
		{
			for (Observer observer : observers) {
				observer.update(this,args);
			}
		}
		changed = false;
	}
	
	public void notifyObservers()
	{
		notifyObservers(null);
	}
	
	
	private boolean isChanged(){
		return this.changed;
	}
	
	public void setChanged(){
		this.changed = true;
	}
	
	public void setAndNotify(String arg)
	{
		setChanged();
		notifyObservers(arg);
	}
	
	public void setAndNotify()
	{
		setChanged();
		notifyObservers(null);
	}
	
}
