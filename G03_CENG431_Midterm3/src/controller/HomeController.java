package controller;

import observation.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Login;
import model.User;
import view.HomeView;
import view.UserView;
public class HomeController {

	private Observer view;
	private Login model;
	
	public HomeController(Observable model, Observer view) {
		this.view=view;
		this.model=(Login) model;
		model.addObserver(view);
		((HomeView)view).addLogoutButtonListener(new LogoutButtonListener());
		((HomeView)view).addFollowerButtonListener(new FollowerButtonListener());
	}
	
	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setUser(null);
			
		}
		
	}
	
	class FollowerButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			UserView uv = new UserView("Deneme",(User)model.getUser());
			UserController uc = new UserController(model.getUser(),uv);
			
		}
		
	}
	
}
