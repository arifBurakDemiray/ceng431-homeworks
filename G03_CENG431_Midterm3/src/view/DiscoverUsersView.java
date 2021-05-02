package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import enums.ButtonState;
import model.UpdatedList;
import model.User;
import observation.Observable;
import observation.Observer;

/**
 * The discover user view is a view which user sees other users in the system.
 * And he/she can follow a user.
 *
 */
public class DiscoverUsersView extends JPanel implements Observer {

	private static final long serialVersionUID = -8544036243765267644L;
	protected Observable model;
	protected JButton followButton; // follow selected user button
	private JScrollPane scrollPaneOfListOfUsersNames; // scroll pane which contains list of users' names
	private JButton back; // back button
	protected JList<String> listOfUsersNames; // JList of users' names
	private JLabel infoMessage; // Text message for user interface
	private JLabel discoverUsers; // Text header for user interface

	public DiscoverUsersView(Observable model) {
		// set the observable and view elements in the view.
		this.model = (User) model;
		AppWindow.FRAME.getContentPane().add(this);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);	
		
		discoverUsers = new JLabel("Discover Users");
		discoverUsers.setBounds(150, 20, 150, 25);
		add(discoverUsers);
		
		// DiscoverUsersController initialises list using update at the start
		// and in the continuation. Default it is empty.
		listOfUsersNames = new JList<String>();
		listOfUsersNames.setVisible(true);

		scrollPaneOfListOfUsersNames = new JScrollPane(listOfUsersNames);
		scrollPaneOfListOfUsersNames.setBounds(150, 50, 200, 200);
		add(scrollPaneOfListOfUsersNames);

		back = new JButton("Back");
		back.setBounds(20, 20, 90, 25);
		add(back);

		followButton = new JButton("Follow");
		followButton.setBounds(250, 250, 100, 25);
		add(followButton);
		
		infoMessage = new JLabel("<html>Select an user by clicking on name. After that click follow button.</html>");
		infoMessage.setBounds(400, 100, 150, 150);
		add(infoMessage);


		setVisible(true);

	}

	/**
	 * The function returns observable of view
	 * 
	 * @return observable
	 */
	protected Observable model() {
		return model;
	}

	/**
	 * The function helps for detecting of clicking follow button using listener
	 * 
	 * @param listener
	 */
	public void addFollowButtonListener(ActionListener followListener) {
		followButton.addActionListener(followListener);
	}

	/**
	 * The function helps for detecting of clicking back button using listener
	 * 
	 * @param listener
	 */
	public void addBackButtonListener(ActionListener backListener) {
		back.addActionListener(backListener);
	}

	/**
	 * The function returns the selected user name of the users list.
	 * 
	 * @param String of selected user name
	 */
	public String getSelectedUser() {
		String name = listOfUsersNames.getSelectedValue();
		return name;
	}

	@Override
	public void update(Observable observable, Object args) {

		// If DiscoverUsersController invokes modifyAndSet() and it notifies with a
		// UpdatedList model,
		// we understand that there is a change in the users name list
		// and update the JList with gotten list from UpdatedList model
		// Or we understand that list is initialised at the start.
		// for example after following a user, it removes the followed user from the
		// list and it updates the list.
		if (args instanceof UpdatedList) {
			listOfUsersNames.setModel(((UpdatedList) args).getListModel());
		}

		// If back button is clicked, remove the view from frame.
		else if (args instanceof ButtonState && args == ButtonState.BACK_BUTTON) {
			AppWindow.FRAME.getContentPane().remove(this);
		}
	}

}
