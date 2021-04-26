package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Login;
import model.User;
import observation.*;

import javax.swing.JLabel;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
public class HomeView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7713286782412153047L;
	private JPanel contentPane;
	private JLabel userName;
	private JButton logoutButton;
	private JButton followersButton;
	private JButton followingsButton;
	private JButton collectionsButton;
	private JScrollPane posts;
	private Login model;

	/**
	 * Create the frame.
	 */
	public HomeView(Observable model) {
		this.model = (Login) model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userName = new JLabel("userName");
		userName.setBounds(26, 15, 65, 14);
		contentPane.add(userName);
		
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(624, 11, 75, 23);
		contentPane.add(logoutButton);
		
		followersButton = new JButton("Followers");
		followersButton.setBounds(10, 40, 100, 23);
		contentPane.add(followersButton);
		
		followingsButton = new JButton("Followings");
		followingsButton.setBounds(10, 73, 100, 23);
		contentPane.add(followingsButton);
		
		collectionsButton = new JButton("Collections");
		collectionsButton.setBounds(10, 107, 100, 23);
		contentPane.add(collectionsButton);
		
		posts = new JScrollPane();
		posts.setBounds(179, 40, 352, 297);
		contentPane.add(posts);
		
		//setVisible(false);
	}
	
	public void addLogoutButtonListener(ActionListener listener) {
		logoutButton.addActionListener(listener);
	}

	@Override
	public void update(Observable observable, Object args) {
		if(args==null)
			this.setVisible(false);
		else {
			this.userName.setText(((User) args).getUserName());
			this.setVisible(true);}
		
	}

	/**
	 * @return the model
	 */
	public Observable getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Observable model) {
		this.model = (Login) model;
	}
	
	@Override
	public void setVisible(boolean isVisible) {
		this.contentPane.setVisible(isVisible);
		super.setVisible(isVisible);
	}
	
}
