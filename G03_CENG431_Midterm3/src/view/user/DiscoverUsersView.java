package view.user;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import fileio.FileController;
import model.User;
import observation.Observable;
import observation.Observer;

public class DiscoverUsersView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8544036243765267644L;
	protected Observable model;
	protected TextField display;
	protected JButton unfollowButton;
	private JButton back;
	protected JList<String> listOfNames;
	private JPanel contentPane;
	private JLabel message;

	public DiscoverUsersView(Observable model) {
		this.model = (User) model;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultListModel<String> listModel = setList();

		listOfNames = new JList<String>(listModel);

		listOfNames.setBounds(150, 150, 200, 200);
		listOfNames.setVisible(true);
		contentPane.add(listOfNames);

		back = new JButton("Back");
		back.setBounds(100, 100, 20, 20);
		contentPane.add(back);

		message = new JLabel("Error");
		message.setBounds(275, 250, 200, 30);
		message.setForeground(Color.RED);

		unfollowButton = new JButton("Follow");
		unfollowButton.setBounds(300, 300, 100, 50);
		contentPane.add(unfollowButton);

		contentPane.add(message);
		message.setVisible(false);
		setVisible(true);

	}

	protected Observable model() {
		return model;
	}

	public void addFollowButtonListener(ActionListener unfollowListener) {
		unfollowButton.addActionListener(unfollowListener);
	}

	public void addBackButtonListener(ActionListener backListener) {
		back.addActionListener(backListener);
	}

	private DefaultListModel<String> setList() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (User user : FileController.users().getContainer()) {
			String temp = ((User) model).getFollowings().getItem(user.getUserName());
			if ( (temp == null) && !user.equals(model) ) {
				listModel.addElement(user.getUserName());
			}

		}
		return listModel;
	}

	public String getSelectedUser() {
		String name = listOfNames.getSelectedValue();
		return name;
	}


	@Override
	public void update(Observable observable, Object args) {
		if (args.equals("followUser")) {
			listOfNames.setModel(setList());

		}
		
		else if(args.equals("back")){
			setVisible(false);
		}
	}

}
