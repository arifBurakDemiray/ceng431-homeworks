package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Outfit;
import model.User;
import observation.Observer;
import storage.IContainer;
import storage.UserContainer;
import view.LoginView;
import view.OutfitView;

public class LoginController {

	private LoginView view;
	private IContainer<User> users;

	public LoginController(Observer view, IContainer<User> users) {
		this.users = users;
		this.view = (LoginView) view;
		this.view.addLoginListener(new LoginListener());
		
	}

	class LoginListener implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			User user = login(view.getUserName().getText(), view.getPassword().getText());
			if (user == null) {
				view.printMessage(false);
			} else {
				view.printMessage(true);
				view.setModel(user);
			}
		}
	}

	public User login(String userName, String password) {
		User found = null; // init found null
		try {// try to get user by name
			found = users.getByName(userName);
			if (found != null) { // if user found, look his password
				final String psw = found.getPassword();
				if (!psw.equals(password)) {
					found = null;
				}
			}

		} catch (ItemNotFoundException | NotSupportedException e) {
		}
		return found;
	}

}
