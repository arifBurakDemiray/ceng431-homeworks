package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
import model.Login;
import model.User;
import observation.Observable;
import observation.Observer;
import service.AuthService;
import view.HomeView;
import view.LoginView;

/**
 * This class handles login screen actions
 */
public class LoginController extends Consumable {

	private LoginView view;
	private Login model;
	private AuthService authService;

	public LoginController(Observable model, Observer view) {
		this.authService = new AuthService();
		this.view = (LoginView) view;
		this.model = (Login) model;
		this.model.addObserver(this.view); // add observer
		this.view.addLoginListener(new LoginListener()); // add listeners
		this.view.addTextListener(new TextClickListener());
	}

	// This class listens login button, if pressed user is going to be checked for
	// login
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			User user = authService.login(view.getUserName().getText(), view.getPassword());
			if (user == null) {
				view.printMessage(false); // this function is for printing credentials wrong message
			} else { // if true means not print the message
				view.printMessage(true);
				HomeView userView = new HomeView(model);
				final Consumable homeController = new HomeController(model, userView);
				model.setUser(user);
				homeController.supressNotUsed();
			}
		}
	}

	// This class listens text areas, if user clicks text, removes the text
	class TextClickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			Object field = e.getSource();
			if (field instanceof JTextField) {
				JTextField temp = (JTextField) field;
				if (temp.getText().equals("Username")) { // if text is initial, remove it
					temp.setText("");
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

}
