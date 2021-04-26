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

import model.User;
import observation.Observable;
import observation.Observer;
import storage.IContainer;

public class FollowingView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2900445353088003976L;
	protected Observable model;
	protected TextField display;
	protected JButton unfollowButton;
	protected JList<String> listOfNames;
	private JPanel contentPane;
	private JLabel message;
	private IContainer<String> followingList;
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
		back.setBounds(23, 11, 89, 23);
		contentPane.add(back);
		
		followingList = ((User) model).getFollowings();
		DefaultListModel<String> listModel = setList();
		
		listOfNames = new JList<String>(listModel);
		
		listOfNames.setBounds(150, 150, 200, 200);
		listOfNames.setVisible(true);
		contentPane.add(listOfNames);

		message = new JLabel("Error");
		message.setBounds(275, 250, 200, 30);
		message.setForeground(Color.RED);
		
		unfollowButton = new JButton("Unfollow");
		unfollowButton.setBounds(261, 350, 89, 23);
		contentPane.add(unfollowButton);

		contentPane.add(message);
		message.setVisible(false);	
		setVisible(true);

	}

	protected Observable model() {
		return model;
	}

	public void addUnfollowButtonListener(ActionListener unfollowListener) {
		unfollowButton.addActionListener(unfollowListener);
	}

	public void addBackButtonListener(ActionListener listener){
		back.addActionListener(listener);
	}

	
	private DefaultListModel<String> setList()
	{
		DefaultListModel<String> listModel = new DefaultListModel<>();		
		for (String user : followingList.getContainer()) {
			listModel.addElement(user);
		}
		return listModel;
	}
	
	public String getSelectedUser()
	{
		  String name = listOfNames.getSelectedValue();
          return name;
	}


	@Override
	public void update(Observable observable, Object args) {
		if(args.equals("unfollowUser"))
		{
			listOfNames.setListData(followingList.toArray());;
		
		}
		else if(args.equals("back")){
			setVisible(false);
		}
	}

}
