package observation;

import exception.ItemNotFoundException;
import storage.IContainer;
import storage.ObserverContainer;

/**
 * This class is for observables
 */
public class Observable {

	private IContainer<Observer> observers; // observer contaienr
	private boolean changed = false; // hold state information

	public Observable() {
		this.observers = new ObserverContainer();
	}

	/**
	 * This function adds an observer to list
	 * 
	 * @param observer that is going to be added
	 */
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}

	/**
	 * This function removes an observer from list
	 * 
	 * @param observer that is going to be removed
	 */
	public void removeObserver(Observer observer) {
		try {
			this.observers.remove(observer);
		} catch (ItemNotFoundException e) {

		}
	}

	/**
	 * This function updates all observers by given arguments
	 * 
	 * @param new informations for observers
	 */
	public void notifyObservers(Object args) {
		if (isChanged())// if state changed
		{ // update all observers
			for (Observer observer : observers) {
				observer.update(this, args);
			}
		}
		changed = false; // and set state false
	}

	/**
	 * Notify observers by not given any arguments
	 */
	public void notifyObservers() {
		notifyObservers(null);
	}

	// This functiın retuns state information
	private boolean isChanged() {
		return this.changed;
	}

	/**
	 * This function changes state of an observable
	 */
	public void setChanged() {
		this.changed = true;
	}

	/**
	 * This function sets and notfiys all observers by given arguments
	 * 
	 * @param args that are new infos for observers
	 */
	public void setAndNotify(Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	/**
	 * This function sets and notfiys all observers by given none arguments
	 */
	public void setAndNotify() {
		setChanged();
		notifyObservers(null);
	}

}
