package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Login;
import observation.Observable;
import observation.Observer;

public class LoginView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7766903621224003360L;
	private JButton loginButton;
	private JPasswordField password;
	private JTextField userName;
	private JLabel message;
	private Login model = null;

	public LoginView(Observable model) {
		this.model = (Login) model;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		loginButton = new JButton("Sign in");
		loginButton.setBounds(300, 200, 100, 30);
		password = new JPasswordField("123456");
		password.setBounds(300, 150, 100, 30);
		userName = new JTextField("furkan");
		userName.setBounds(300, 100, 100, 30);
		message = new JLabel("Incorrect login, please try again");
		message.setBounds(275, 250, 200, 30);
		message.setForeground(Color.RED);

		add(message);
		message.setVisible(false);
		add(loginButton);
		add(password);
		add(userName);
		setVisible(true);

	}

	public Observable getModel() {
		return model;
	}

	public void setModel(Observable observable) {
		this.model = (Login) observable;
	}

	public void addLoginListener(ActionListener loginListener) {
		loginButton.addActionListener(loginListener);
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void addTextListener(MouseListener textListener) {
		userName.addMouseListener(textListener);
		password.addMouseListener(textListener);
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JTextField getUserName() {
		return userName;
	}

	public void setUserName(JTextField userName) {
		this.userName = userName;
	}

	public void printMessage(boolean isAuthenticated) {
		if (isAuthenticated)
			setVisible(false);
		else
			message.setVisible(true);

	}

	@Override
	public void update(Observable observable, Object args) {
		if (args == null)
			this.setVisible(true);
	}

}
