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

public class FollowerView extends JPanel implements Observer {

	private static final long serialVersionUID = -9191185559293341072L;
	protected Observable model;
	protected TextField display;
	private JButton back;
	private JScrollPane scrollPaneOfListOfFollowersNames;
	protected JList<String> listOfFollowersNames;

	public FollowerView(Observable model) {
		this.model = (User) model;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		AppWindow.FRAME.getContentPane().add(this);
		setLayout(null);

		back = new JButton("Back");
		back.setBounds(20, 20, 90, 25);
		add(back);

		listOfFollowersNames = new JList<String>();
		listOfFollowersNames.setVisible(true);

		scrollPaneOfListOfFollowersNames = new JScrollPane(listOfFollowersNames);
		scrollPaneOfListOfFollowersNames.setBounds(150, 50, 200, 200);
		add(scrollPaneOfListOfFollowersNames);

		setVisible(true);

	}

	protected Observable model() {
		return model;
	}

	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}



	@Override
	public void update(Observable observable, Object args) {

		if (args instanceof ButtonState && args==ButtonState.BACK_BUTTON) {
			AppWindow.FRAME.getContentPane().remove(this);
		}
		else if(args instanceof UpdatedList)
		{
			listOfFollowersNames.setModel( ((UpdatedList)args).getListModel());
		}

	}

}
