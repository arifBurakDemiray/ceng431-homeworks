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
 * The following view is a view which user sees users which he/she follows And
 * he/she can unfollow a user.
 *
 */
public class FollowingView extends JPanel implements Observer {

	private static final long serialVersionUID = 2900445353088003976L;
	protected Observable model;
	protected JButton unfollowButton; // unfollow selected user button
	private JScrollPane scrollPaneOfListOfFollowingsNames; // scroll pane which contains list of followed users' names
	protected JList<String> listOfFollowingsNames; // JList of followed users' names
	private JButton back; // back button
	private JLabel infoMessage; // Text message for user interface
	private JLabel myFollowings; // Text header for user interface

	public FollowingView(Observable model) {
		// set the observable and view elements in the view.
		this.model = (User) model;
		AppWindow.FRAME.getContentPane().add(this);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		back = new JButton("Back");
		back.setBounds(20, 20, 90, 25);
		add(back);
		
		myFollowings = new JLabel("My Followings");
		myFollowings.setBounds(150, 20, 150, 25);
		add(myFollowings);
		
		// FollowingController initialises list using update at the start
		// and in the continuation. Default it is empty.
		listOfFollowingsNames = new JList<String>();
		listOfFollowingsNames.setVisible(true);

		scrollPaneOfListOfFollowingsNames = new JScrollPane(listOfFollowingsNames);
		scrollPaneOfListOfFollowingsNames.setBounds(150, 50, 200, 200);
		add(scrollPaneOfListOfFollowingsNames);

		unfollowButton = new JButton("Unfollow");
		unfollowButton.setBounds(250, 250, 100, 25);
		add(unfollowButton);
		
		infoMessage = new JLabel("<html>Select an user by clicking on name. After that click unfollow button.</html>");
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
	 * The function helps for detecting of clicking unfollow button using listener
	 * 
	 * @param listener
	 */
	public void addUnfollowButtonListener(ActionListener unfollowListener) {
		unfollowButton.addActionListener(unfollowListener);
	}

	/**
	 * The function helps for detecting of clicking back button using listener
	 * 
	 * @param listener
	 */
	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	/**
	 * The function returns the selected user name of the followed users list.
	 * 
	 * @param String of selected user name
	 */
	public String getSelectedUser() {
		String name = listOfFollowingsNames.getSelectedValue();
		return name;
	}

	@Override
	public void update(Observable observable, Object args) {

		// If FollowingController invokes modifyAndSet() and it notifies with a
		// UpdatedList model,
		// we understand that there is a change in the followed users name list
		// and update the JList with gotten list from UpdatedList model
		// Or we understand that list is initialised at the start.
		// for example after unfollowing a user, it removes the unfollowed user from the
		// list and it updates the list.
		if (args instanceof UpdatedList) {
			listOfFollowingsNames.setModel(((UpdatedList) args).getListModel());
		}

		// If back button is clicked, remove the view from frame.
		else if (args instanceof ButtonState && args == ButtonState.BACK_BUTTON) {
			AppWindow.FRAME.getContentPane().remove(this);
		}
	}

}
