package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Rates;
import observation.Observable;
import observation.Observer;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

public class TopRateView extends JFrame implements Observer{

	private static final long serialVersionUID = 6980364354683833411L;
	private JPanel contentPane;
	private JLabel topLike;
	private JLabel topDislike;
	private JLabel topUser;
	private JLabel topLikeValue;
	private JLabel topDislikeValue;
	private JLabel topUserValue;
	private JButton back;
	private Observable model;


	/**
	 * Create the frame.
	 */
	public TopRateView(Observable model) {
		
		this.model = model;
		Rates temp = (Rates)model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		topLike = new JLabel("Top Liked Outfit");
		topLike.setForeground(Color.BLUE);
		topLike.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topLike.setBounds(74, 66, 194, 61);
		contentPane.add(topLike);
		
		topUser = new JLabel("Top Followed User");
		topUser.setForeground(Color.MAGENTA);
		topUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topUser.setBounds(74, 249, 183, 61);
		contentPane.add(topUser);
		
		topDislike = new JLabel("Top Disliked Outfit");
		topDislike.setForeground(Color.RED);
		topDislike.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topDislike.setBounds(74, 158, 199, 61);
		contentPane.add(topDislike);
		
		back = new JButton("Back");
		back.setBounds(23, 11, 89, 23);
		contentPane.add(back);
		
		topLikeValue = new JLabel(temp.topLikedOutfitType);
		topLikeValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topLikeValue.setBounds(380, 85, 130, 23);
		contentPane.add(topLikeValue);
		
		topDislikeValue = new JLabel(temp.topDislikedOutfitType);
		topDislikeValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topDislikeValue.setBounds(380, 181, 88, 23);
		contentPane.add(topDislikeValue);
		
		topUserValue = new JLabel(temp.topFollewedUserName);
		topUserValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topUserValue.setBounds(380, 272, 89, 23);
		contentPane.add(topUserValue);
		setVisible(true);
	}


	public void addBackButtonListener(ActionListener listener){
		back.addActionListener(listener);
	}


	@Override
	public void update(Observable observable, Object args) {
		if(args.equals("back"))
			setVisible(false);
	}

}
