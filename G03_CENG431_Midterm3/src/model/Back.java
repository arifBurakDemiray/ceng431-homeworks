package model;

import enums.ButtonState;
import observation.Observable;

public class Back extends Observable {

	@Override
	public void notifyObservers()
	{
		notifyObservers(ButtonState.BACK_BUTTON);
	}

}
