package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Login;
import model.User;
import observation.Observable;
import observation.Observer;
import storage.IContainer;
import view.LoginView;

public class LoginController {

	private LoginView view;
	private IContainer<User> users;
	private Login model;

	public LoginController(Observable model,Observer view, IContainer<User> users) {
		this.users = users;
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
			User user = login(view.getUserName().getText(), view.getPassword().getText());
			if (user == null) {
				view.printMessage(false);
			} else {
				view.printMessage(true);
				model.setUser(user);
			}
		}
	}


	
	class TextClickListener implements MouseListener{

	

		@Override
		public void mouseClicked(MouseEvent e) {
			Object field = e.getSource();
			if(field instanceof JTextField) {
				JTextField temp = (JTextField)field;
				if(temp.getText().equals("Username")) {
					temp.setText("");
				}
			}
//			else if(field instanceof JPasswordField) {
//				JPasswordField temp = (JPasswordField)field;
//					temp.setText("");
//				
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
