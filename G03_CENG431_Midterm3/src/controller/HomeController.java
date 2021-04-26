package controller;

import observation.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Login;
import view.HomeView;
public class HomeController {

	private Observer view;
	private Login model;
	
	public HomeController(Observable model, Observer view) {
		this.view=view;
		this.model=(Login) model;
		model.addObserver(view);
		((HomeView)view).addLogoutButtonListener(new LogoutButtonListener());
	}
	
	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setUser(null);
			
		}
		
	}
	
}
