package view.user;

import java.awt.TextField;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import model.User;
import observation.Observable;
import observation.Observer;
import storage.IContainer;

public class FollowingView extends JFrame implements Observer {

	private static final long serialVersionUID = 2900445353088003976L;
	protected Observable model;
	protected TextField display;
	protected JButton unfollowButton;
	private JScrollPane scrollPaneOfListOfFollowingsNames;
	protected JList<String> listOfFollowingsNames;
	private JPanel contentPane;
	private JButton back;

	public FollowingView(Observable model) {
		this.model = (User) model;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		back = new JButton("Back");
		back.setBounds(20, 20, 90, 25);
		contentPane.add(back);

		
		DefaultListModel<String> listModel = setList();

		listOfFollowingsNames = new JList<String>(listModel);
		listOfFollowingsNames.setVisible(true);

		scrollPaneOfListOfFollowingsNames = new JScrollPane(listOfFollowingsNames);
		scrollPaneOfListOfFollowingsNames.setBounds(150, 50, 200, 200);
		contentPane.add(scrollPaneOfListOfFollowingsNames);

		unfollowButton = new JButton("Unfollow");
		unfollowButton.setBounds(250, 250, 100, 25);
		contentPane.add(unfollowButton);

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
		if (args.equals("unfollowUser")) {
			listOfFollowingsNames.setModel(setList());
		} else if (args.equals("back")) {
			setVisible(false);
		}
	}

}