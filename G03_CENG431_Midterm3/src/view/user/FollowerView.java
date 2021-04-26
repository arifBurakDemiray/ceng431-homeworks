package view;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.User;
import observation.Observable;
import observation.Observer;

public class FollowerVi extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7677309279033720304L;
	protected User model;
	protected TextField display;
	protected JButton followButton;
	protected JButton unfollowButton;
	protected Box followingsList;

	
	public FollowerView(String label, User model) {
		this.model = model;
		model.addObserver(this);
		
		Box followingsList = Box.createVerticalBox();	
		followingsList.setOpaque(true);
		followingsList.setBackground(Color.BLUE);
		
		int i=0;
		for (String user :  model.getFollowings().getContainer()) {			
			
			Box userBox = Box.createHorizontalBox();
			userBox.setOpaque(true);
			userBox.setBackground(Color.RED);
			
			unfollowButton = new JButton("Unfollow");
			unfollowButton.setBounds(60, 50, 50, 50);
			userBox.add(unfollowButton, 0);
			
			JLabel userText = new JLabel (user);
			userText.setBounds(0,50,50,50);
			userBox.add(userText, 0);
		
			followingsList.add(userBox);		
			i++;
		}
		
		
		followingsList.setBounds(0,0, 120,i*50);			
		add(followingsList);
		
		
		setSize(1000,1000);  
		setLayout(null);//using no layout manager  
		setVisible(false);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
	}

	protected User model() {
		return model;
	}
	
	public void addFollowListener(ActionListener followListener) {		
        followButton.addActionListener(followListener);     
    }
	
	public void addUnfollowListener(ActionListener unfollowListener) {
		unfollowButton.addActionListener(unfollowListener);       
    }

	public static class CloseListener extends WindowAdapter { // close all related windows
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
	

	@Override
	public void update(Observable observable, Object args) {
	
	}

}
