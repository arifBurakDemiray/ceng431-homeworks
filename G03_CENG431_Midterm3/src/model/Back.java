package model;

import enums.ButtonState;
import observation.Observable;

/**
 * The model for the Back button which overrides notfitObservers
 *
 */
public class Back extends Observable {

	@Override
	public void notifyObservers()
	{
		notifyObservers(ButtonState.BACK_BUTTON);
	}

}
