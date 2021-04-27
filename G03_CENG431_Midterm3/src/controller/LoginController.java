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
import view.LoginView;

public class LoginController {

	private LoginView view;
	private Login model;
	private AuthService authService;

	public LoginController(Observable model, Observer view) {
		this.authService = new AuthService();
		this.view = (LoginView) view;
		this.model = (Login) model;
		this.model.addObserver(this.view);
		this.view.addLoginListener(new LoginListener());
		this.view.addTextListener(new TextClickListener());

	}

	class LoginListener implements ActionListener {
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			User user = authService.login(view.getUserName().getText(), view.getPassword().getText());
			if (user == null) {
				view.printMessage(false);
			} else {
				view.printMessage(true);
				model.setUser(user);
			}
		}
	}

	class TextClickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			Object field = e.getSource();
			if (field instanceof JTextField) {
				JTextField temp = (JTextField) field;
				if (temp.getText().equals("Username")) {
					temp.setText("");
				}
			}
//			else if(field instanceof JPasswordField) {
//				JPasswordField temp = (JPasswordField)field;
//					temp.setText("");//				
//			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
