package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import enums.ButtonState;
import observation.Observable;
import observation.Observer;
import view.TopRateView;

/**
* This class is responsible for control the top rate view
*/
public class TopRateController extends Consumable {

	private Observer view;
	private Observable model;

	public TopRateController(Observable model, Observer view) {
		this.view = view;
		this.model = model;
		model.addObserver(view);
		((TopRateView) view).addBackButtonListener(new BackButtonListener()); //add back button
	}

	//if back button pressed, remove observer and go back
	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setChanged();
			model.notifyObservers(ButtonState.BACK_BUTTON);
			model.removeObserver(view);
		}
	}

}
