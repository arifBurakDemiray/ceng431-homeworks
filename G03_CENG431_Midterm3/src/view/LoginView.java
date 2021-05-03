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

/**
 * The login view is a view which user can log in the system.
 *
 */
public class LoginView extends JPanel implements Observer {


	private static final long serialVersionUID = 7766903621224003360L;
	private JButton loginButton; // login button
	private JPasswordField password; // password field
	private JTextField userName; // user name field
	private JLabel message; // warning message according to the situation
	private Login model = null;

	public LoginView(Observable model) {
		// set the observable and view elements in the view.
		this.model = (Login) model;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		loginButton = new JButton("Sign in");
		loginButton.setBounds(300, 200, 100, 30);
		add(loginButton);
		
		password = new JPasswordField("Password");
		password.setBounds(300, 150, 100, 30);
		add(password);
		
		userName = new JTextField("Username");
		userName.setBounds(300, 100, 100, 30);
		add(userName);
		
		message = new JLabel("Incorrect login, please try again");
		message.setBounds(275, 250, 200, 30);
		message.setForeground(Color.RED);
		add(message);
		message.setVisible(false);
		
		AppWindow.FRAME.getContentPane().add(this);
		AppWindow.FRAME.setVisible(true);

	}
	
	/**
	 * The function returns observable of view
	 * 
	 * @return observable
	 */
	public Observable getModel() {
		return model;
	}
	
	/**
	 * The function sets the observable of view.
	 * @param observable
	 */
	public void setModel(Observable observable) {
		this.model = (Login) observable;
	}
	
	/**
	 * The function helps for detecting of clicking login button using listener
	 * 
	 * @param listener
	 */
	public void addLoginListener(ActionListener loginListener) {
		loginButton.addActionListener(loginListener);
	}
	
	/**
	 * The function helps for detecting of change in the
	 * user name field using listener
	 * 
	 * @param listener
	 */
	public void addTextListener(MouseListener textListener) {
		userName.addMouseListener(textListener);
	}
	
	/**
	 * The function returns the filled password
	 * 
	 * @return String = password text
	 */
	public char[] getPassword() {
		return password.getPassword();
	}

	/**
	 * The function returns the filled user name
	 * 
	 * @return String = user name text
	 */
	public JTextField getUserName() {
		return userName;
	}

	/**
	 * If user is not authenticated, show the warning message
	 * @param isAuthenticated
	 */
	public void printMessage(boolean isAuthenticated) {
		if (isAuthenticated)
			setVisible(false);
		else
			message.setVisible(true);
	}

	@Override
	public void update(Observable observable, Object args) {
		if (args == null){
			password.setText("");
			userName.setText("");
			message.setVisible(false);
			this.setVisible(true);}
	}

}
