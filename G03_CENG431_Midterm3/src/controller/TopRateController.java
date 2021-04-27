package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fileio.OutfitRepository;
import fileio.UserRepository;
import observation.Observable;
import observation.Observer;
import view.TopRateView;

public class TopRateController {

	private Observer view;
	private Observable model;

	public TopRateController(Observable model, Observer view) {
		this.view = view;
		this.model = model;
		model.addObserver(view);
		((TopRateView)view).addBackButtonListener(new BackButtonListener());
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setChanged();
			model.notifyObservers("back");
			model.removeObserver(view);
		}
	}


}
