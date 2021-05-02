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
 * The follower view is a view which user sees followers
 *
 */
public class FollowerView extends JPanel implements Observer {

	private static final long serialVersionUID = -9191185559293341072L;
	protected Observable model;
	private JButton back; // back button
	private JScrollPane scrollPaneOfListOfFollowersNames; // scroll pane which contains list of followers' names
	protected JList<String> listOfFollowersNames; // JList of followers' names
	private JLabel myFollowers; // Text header for user interface

	public FollowerView(Observable model) {
		// set the observable and view elements in the view.
		this.model = (User) model;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		AppWindow.FRAME.getContentPane().add(this);
		setLayout(null);

		back = new JButton("Back");
		back.setBounds(20, 20, 90, 25);
		add(back);
		
		myFollowers = new JLabel("My Followers");
		myFollowers.setBounds(150, 20, 150, 25);
		add(myFollowers);
		
		// FollowerController initialises list using update at the start 
		// and in the continuation. Default it is empty.
		listOfFollowersNames = new JList<String>();
		listOfFollowersNames.setVisible(true);

		scrollPaneOfListOfFollowersNames = new JScrollPane(listOfFollowersNames);
		scrollPaneOfListOfFollowersNames.setBounds(150, 50, 200, 200);
		add(scrollPaneOfListOfFollowersNames);
		
		

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
	 * The function helps for detecting of clicking back button using listener
	 * 
	 * @param listener
	 */
	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	@Override
	public void update(Observable observable, Object args) {
		
		// If back button is clicked, remove the view from frame.
		if (args instanceof ButtonState && args == ButtonState.BACK_BUTTON) {
			AppWindow.FRAME.getContentPane().remove(this);
		}

		// If FollowerUser invokes modifyAndSet() and it notifies with a
		// UpdatedList model, we understand that JList is initialised
		else if (args instanceof UpdatedList) {
			listOfFollowersNames.setModel(((UpdatedList) args).getListModel());
		}

	}

}
