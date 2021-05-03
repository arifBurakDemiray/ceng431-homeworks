package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import enums.ButtonState;
import model.Rates;
import observation.Observable;
import observation.Observer;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

/**
 * The top rate view is a view which shows the most liked and disliked outfit,
 * most followed user
 *
 */
public class TopRateView extends JPanel implements Observer {

	private static final long serialVersionUID = 6980364354683833411L;
	private JLabel topLike; // text of "Top Liked Outfit"
	private JLabel topDislike; // text of "Top Disliked Outfit"
	private JLabel topUser; // text of "Top Followed User"
	private JLabel topLikeValue; // text of name of most liked outfit
	private JLabel topDislikeValue; // text of name of most disliked outfit
	private JLabel topUserValue; // text of name of most followed user
	private JButton back; // back button
	private Observable model;

	/**
	 * Create the frame.
	 */
	public TopRateView(Observable Model) {
		// set the observable and view elements in the view.
		this.model = Model;
		Rates temp = (Rates) model;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		AppWindow.FRAME.getContentPane().add(this);

		topLike = new JLabel("Top Liked Outfit");
		topLike.setForeground(Color.BLUE);
		topLike.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topLike.setBounds(74, 66, 194, 61);
		add(topLike);

		topUser = new JLabel("Top Followed User");
		topUser.setForeground(Color.MAGENTA);
		topUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topUser.setBounds(74, 249, 183, 61);
		add(topUser);

		topDislike = new JLabel("Top Disliked Outfit");
		topDislike.setForeground(Color.RED);
		topDislike.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topDislike.setBounds(74, 158, 199, 61);
		add(topDislike);

		back = new JButton("Back");
		back.setBounds(23, 11, 89, 23);
		add(back);

		topLikeValue = new JLabel(temp.topLikedOutfitType);
		topLikeValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topLikeValue.setBounds(380, 85, 200, 23);
		add(topLikeValue);

		topDislikeValue = new JLabel(temp.topDislikedOutfitType);
		topDislikeValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topDislikeValue.setBounds(380, 181, 200, 23);
		add(topDislikeValue);

		topUserValue = new JLabel(temp.topFollewedUserName);
		topUserValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topUserValue.setBounds(380, 272, 200, 23);
		add(topUserValue);
		setVisible(true);
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
		// If back button is clicked, remove the view from FRAME
		if (args instanceof ButtonState && args == ButtonState.BACK_BUTTON)
			AppWindow.FRAME.getContentPane().remove(this);
	}

}
