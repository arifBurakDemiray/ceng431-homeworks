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
import fileio.UserRepository;
import model.User;
import observation.Observable;
import observation.Observer;

public class DiscoverUsersView extends JFrame implements Observer {

	private static final long serialVersionUID = -8544036243765267644L;
	protected Observable model;
	protected TextField display;
	protected JButton followButton;
	private JScrollPane scrollPaneOfListOfUsersNames;
	private JButton back;
	protected JList<String> listOfUsersNames;
	private JPanel contentPane;

	public DiscoverUsersView(Observable model) {
		this.model = (User) model;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultListModel<String> listModel = setList();

		listOfUsersNames = new JList<String>(listModel);
		listOfUsersNames.setVisible(true);

		scrollPaneOfListOfUsersNames = new JScrollPane(listOfUsersNames);
		scrollPaneOfListOfUsersNames.setBounds(150, 50, 200, 200);
		contentPane.add(scrollPaneOfListOfUsersNames);

		back = new JButton("Back");
		back.setBounds(20, 20, 90, 25);
		contentPane.add(back);

		followButton = new JButton("Follow");
		followButton.setBounds(250, 250, 100, 25);
		contentPane.add(followButton);

	
		setVisible(true);

	}

	protected Observable model() {
		return model;
	}

	public void addFollowButtonListener(ActionListener unfollowListener) {
		followButton.addActionListener(unfollowListener);
	}

	public void addBackButtonListener(ActionListener backListener) {
		back.addActionListener(backListener);
	}

	private DefaultListModel<String> setList() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		UserRepository userRepository = new UserRepository();
		for (User user :userRepository.getUsers().getContainer()) {
			String temp = ((User) model).getFollowings().getItem(user.getUserName());
			if ((temp == null) && !user.equals(model)) {
				listModel.addElement(user.getUserName());
			}

		}
		return listModel;
	}

	public String getSelectedUser() {
		String name = listOfUsersNames.getSelectedValue();
		return name;
	}

	@Override
	public void update(Observable observable, Object args) {
		if (args.equals("followUser")) {
			listOfUsersNames.setModel(setList());
		}

		else if (args.equals("back")) {
			setVisible(false);
		}
	}

}