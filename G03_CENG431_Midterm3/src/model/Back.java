package model;

import observation.Observable;

public class Back extends Observable {

	@Override
	public void notifyObservers()
	{
		notifyObservers("back");
	}

}
