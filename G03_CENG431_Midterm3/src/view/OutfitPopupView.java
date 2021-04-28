package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.CommentModel;
import observation.Observable;
import observation.Observer;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JList;

public class OutfitPopupView extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8463308497742322444L;

	private JPanel contentPane;
	private JList<CommentModel> list;
	private Observable model;
	private JScrollPane scrollPane;
	private JButton like;
	private JButton dislike;
	private JEditorPane commentArea;
	private JButton comment;
	/**
	 * Create the frame.
	 */
	public OutfitPopupView(Observable Model) {
		this.model = Model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(28, 34, 73, 93);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(111, 34, 232, 231);
		contentPane.add(scrollPane);
		
		list = new JList<CommentModel>();
		scrollPane.setViewportView(list);
		
		like = new JButton("Like");
		like.setBounds(28, 172, 51, 23);
		contentPane.add(like);
		
		dislike = new JButton("Dislike");
		dislike.setBounds(28, 206, 61, 23);
		contentPane.add(dislike);
		
		comment = new JButton("Comment");
		comment.setBounds(181, 365, 89, 23);
		contentPane.add(comment);
		
		commentArea = new JEditorPane();
		commentArea.setBounds(121, 276, 211, 61);
		contentPane.add(commentArea);
		
		setVisible(true);
	}


	public void AddLikeListener(ActionListener listener) {
		like.addActionListener(listener);
	}
	
	public void AddDislikeListener(ActionListener listener) {
		dislike.addActionListener(listener);
	}
	
	public void AddCommentListener(ActionListener listener) {
		comment.addActionListener(listener);
	}
	
	@Override
	public void update(Observable observable, Object args) {
		// TODO Auto-generated method stub
		
	}
}
