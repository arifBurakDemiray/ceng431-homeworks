package view;

import java.awt.TextField;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import enums.ButtonState;
import model.UpdatedList;
import model.User;
import observation.Observable;
import observation.Observer;

public class FollowingView extends JPanel implements Observer {

	private static final long serialVersionUID = 2900445353088003976L;
	protected Observable model;
	protected TextField display;
	protected JButton unfollowButton;
	private JScrollPane scrollPaneOfListOfFollowingsNames;
	protected JList<String> listOfFollowingsNames;
	private JButton back;

	public FollowingView(Observable model) {
		this.model = (User) model;
		AppWindow.FRAME.getContentPane().add(this);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		back = new JButton("Back");
		back.setBounds(20, 20, 90, 25);
		add(back);

		listOfFollowingsNames = new JList<String>();
		listOfFollowingsNames.setVisible(true);

		scrollPaneOfListOfFollowingsNames = new JScrollPane(listOfFollowingsNames);
		scrollPaneOfListOfFollowingsNames.setBounds(150, 50, 200, 200);
		add(scrollPaneOfListOfFollowingsNames);

		unfollowButton = new JButton("Unfollow");
		unfollowButton.setBounds(250, 250, 100, 25);
		add(unfollowButton);

		setVisible(true);

	}

	protected Observable model() {
		return model;
	}

	public void addUnfollowButtonListener(ActionListener unfollowListener) {
		unfollowButton.addActionListener(unfollowListener);
	}

	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	

	public String getSelectedUser() {
		String name = listOfFollowingsNames.getSelectedValue();
		return name;
	}

	@Override
	public void update(Observable observable, Object args) {
		if (args instanceof UpdatedList) {
			listOfFollowingsNames.setModel(((UpdatedList)args).getListModel());
		} else if (args instanceof ButtonState && args==ButtonState.BACK_BUTTON){
			AppWindow.FRAME.getContentPane().remove(this);
		}
	}

}
