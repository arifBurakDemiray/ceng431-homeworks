package view.user;

import java.awt.TextField;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.User;
import observation.Observable;
import observation.Observer;
import storage.IContainer;

public class FollowerView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9191185559293341072L;
	protected Observable model;
	protected TextField display;
	private JButton back;
	protected JList<String> listOfNames;
	private JPanel contentPane;
	private IContainer<String> followersList;

	public FollowerView(Observable model) {
		this.model = (User) model;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		back = new JButton("back");
		back.setBounds(100, 100, 20, 10);
		contentPane.add(back);

		followersList = ((User) model).getFollowers();
		DefaultListModel<String> listModel = setList();

		listOfNames = new JList<String>(listModel);

		listOfNames.setBounds(150, 150, 200, 200);
		listOfNames.setVisible(true);
		contentPane.add(listOfNames);
		setVisible(true);

	}

	protected Observable model() {
		return model;
	}

	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	private DefaultListModel<String> setList() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String user : followersList.getContainer()) {
			listModel.addElement(user);
		}
		return listModel;
	}


	@Override
	public void update(Observable observable, Object args) {

		if (args.equals("back")) {
			setVisible(false);
		}

	}

}
