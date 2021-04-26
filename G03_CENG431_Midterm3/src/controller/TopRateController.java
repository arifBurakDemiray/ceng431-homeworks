package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.User;
import observation.Observable;
import observation.Observer;
import view.TopRateView;

public class TopRateController {

	private Observer view;
	private Observable model;

	public TopRateController(Observable model, Observer view) {
		this.view = view;
		this.model = (User) model;
		model.addObserver(view);
		((TopRateView)view).addBackButtonListener(new BackButtonListener());
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			((User) model).setAndNotify("back");
			model.removeObserver(view);
		}
	}
	
	

}
