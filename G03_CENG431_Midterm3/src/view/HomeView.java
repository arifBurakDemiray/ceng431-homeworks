package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import enums.ButtonState;
import model.Login;
import model.User;
import observation.*;
import service.CollectionPrintService;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * The home view is a view which user sees other users' collections. And he/she
 * can reach the following view, follower view , discover users view, collection
 * view, top rated things view using buttons
 *
 */
public class HomeView extends JPanel implements Observer {

	private static final long serialVersionUID = 7713286782412153047L;
	private JLabel userName; // user name of user
	private JButton logoutButton; // logout button
	private JButton followersButton; // followers button to reach follower view
	private JButton followingsButton;// followings button to reach following view
	private JButton discoverUsersButton; // discoverUsers button to reach discover users view
	private JButton collectionsButton; // collections button to reach collection view
	private JButton topRateButton; // topRate button to reach top rate view
	private JList<String> collectionListOfFollowedUsers; // JList of followed users' collections with outfits.
	private JScrollPane posts; // scroll pane which contains list of followed users' collections with outfits.
	private Login model;
	private JLabel infoMessage; // Text message for user interface
	private JLabel postHeader; // "Posts of followed user" text for user interface

	/**
	 * Create the frame.
	 */
	public HomeView(Observable model) {

		// set the observable and view elements in the view.
		this.model = (Login) model;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		userName = new JLabel("userName");
		userName.setBounds(25, 15, 150, 15);
		add(userName);

		logoutButton = new JButton("Logout");
		logoutButton.setBounds(625, 10, 75, 25);
		add(logoutButton);

		followersButton = new JButton("Followers");
		followersButton.setBounds(10, 40, 125, 25);
		add(followersButton);

		followingsButton = new JButton("Followings");
		followingsButton.setBounds(10, 75, 125, 25);
		add(followingsButton);

		collectionsButton = new JButton("Collections");
		collectionsButton.setBounds(10, 105, 125, 25);
		add(collectionsButton);

		discoverUsersButton = new JButton("Discover Users");
		discoverUsersButton.setBounds(10, 140, 125, 25);
		add(discoverUsersButton);

		topRateButton = new JButton("Top Rates");
		topRateButton.setBounds(10, 175, 125, 25);
		add(topRateButton);
		
		postHeader = new JLabel("Collections of followed users");
		postHeader.setBounds(180, 20, 250, 20);
		add(postHeader);
		
		posts = new JScrollPane();
		posts.setBounds(180, 40, 320, 300);

		// HomeController initialises list using update at the start
		// and in the continuation. Default it is empty.
		collectionListOfFollowedUsers = new JList<String>();
		posts.setViewportView(collectionListOfFollowedUsers);
		add(posts);
		
		infoMessage = new JLabel("<html>Review an outfit by clicking on name twice.</html>");
		infoMessage.setBounds(550, 100, 100, 200);
		add(infoMessage);
		
		AppWindow.FRAME.getContentPane().add(this);

	}

	/**
	 * The function helps for detecting of clicking logout button using listener
	 * 
	 * @param listener
	 */
	public void addLogoutButtonListener(ActionListener listener) {
		logoutButton.addActionListener(listener);
	}

	/**
	 * The function helps for detecting of clicking following button using listener
	 * 
	 * @param listener
	 */
	public void addFollowingButtonListener(ActionListener listener) {
		followingsButton.addActionListener(listener);
	}

	/**
	 * The function helps for detecting of clicking follower button using listener
	 * 
	 * @param listener
	 */
	public void addFollowerButtonListener(ActionListener listener) {
		followersButton.addActionListener(listener);
	}

	/**
	 * The function helps for detecting of clicking discover users button using
	 * listener
	 * 
	 * @param listener
	 */
	public void addDiscoverUsersButtonListener(ActionListener listener) {
		discoverUsersButton.addActionListener(listener);
	}

	/**
	 * The function helps for detecting of clicking top rate button using listener
	 * 
	 * @param listener
	 */
	public void addTopRateButtonListener(ActionListener listener) {
		topRateButton.addActionListener(listener);
	}

	/**
	 * The function helps for detecting of clicking collection button using listener
	 * 
	 * @param listener
	 */
	public void addCollectionButtonListener(ActionListener listener) {
		collectionsButton.addActionListener(listener);
	}

	/**
	 * The function helps for detecting of selecting a outfit of a collection in the
	 * followed users' collections using listener
	 * 
	 * @param listener
	 */
	public void addSelectCollectionListener(MouseListener listener) {
		collectionListOfFollowedUsers.addMouseListener(listener);
	}


	/**
	 * The function returns the selected outfit of the followed users' collections'
	 * list.
	 * 
	 * @param String of selected user name
	 */
	public String getListSelected() {
		return collectionListOfFollowedUsers.getSelectedValue();
	}

	/**
	 * If a user is gotten in the update method, set the followed users' collections
	 * list according the gotten user's followed users
	 * 
	 * @param user
	 */
	private void updateScroll(User user) {
		final var listModel = (new CollectionPrintService(user)).getScroolString();
		collectionListOfFollowedUsers.setModel(listModel);
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
	 * @param model to set
	 */
	public void setModel(Observable model) {
		this.model = (Login) model;
	}
	
	@Override
	public void update(Observable observable, Object args) {

		if (args == null) {
			AppWindow.FRAME.getContentPane().remove(this);
		}
		// If back button is clicked,
		else if (args instanceof ButtonState && args == ButtonState.BACK_BUTTON) {
			updateScroll((User) model.getUser());
			setVisible(true);
		}

		// If HomeController invokes modifyAndSet() and it notifies with a
		// User model,
		// we understand that home view is added as observer to the user.
		// and initialise the necessary view elements.
		else if (args instanceof User) {
			((User) args).addObserver(this);
			this.userName.setText(((User) args).getUserName());
			this.setVisible(true);
			updateScroll((User) args); // update the followed users' collections list according to gotten user.
			collectionListOfFollowedUsers.setVisible(true);
			posts.setVisible(true);
		}
	}

}
