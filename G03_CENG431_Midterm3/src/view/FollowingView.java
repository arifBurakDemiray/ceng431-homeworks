package view;

import java.awt.TextField;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import enums.ButtonState;
import model.User;
import observation.Observable;
import observation.Observer;
import storage.IContainer;

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

		DefaultListModel<String> listModel = setList();

		listOfFollowingsNames = new JList<String>(listModel);
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

	private DefaultListModel<String> setList() {
		IContainer<String> followingList = ((User) model).getFollowings();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String user : followingList.getContainer()) {
			listModel.addElement(user);
		}
		return listModel;
	}

	public String getSelectedUser() {
		String name = listOfFollowingsNames.getSelectedValue();
		return name;
	}

	@Override
	public void update(Observable observable, Object args) {
		if (args instanceof ButtonState && args==ButtonState.UNFOLLOW_BUTTON) {
			listOfFollowingsNames.setModel(setList());
		} else if (args instanceof ButtonState && args==ButtonState.BACK_BUTTON){
			setVisible(false);
		}
	}

}
