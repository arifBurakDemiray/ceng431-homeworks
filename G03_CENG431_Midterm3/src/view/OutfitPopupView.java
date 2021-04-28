package view;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.ColorResult;
import model.Comment;
import model.OutfitReview;
import observation.Observable;
import observation.Observer;
import storage.IContainer;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JList;

public class OutfitPopupView extends JFrame implements Observer{

	private static final long serialVersionUID = 8463308497742322444L;

	private JPanel contentPane;
	private JList<String> listOfComments;
	private Observable model;
	private JScrollPane scrollPane;
	private JButton like;
	private JButton dislike;
	private JEditorPane commentArea;
	private JLabel outfitInfo;
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
		
		outfitInfo = new JLabel(model.toString());
		outfitInfo.setBounds(120, 20, 200, 100);
		contentPane.add(outfitInfo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 130, 200, 170);
		contentPane.add(scrollPane);
		
		
		DefaultListModel<String> listModel = setCommentList();
		listOfComments = new JList<String>(listModel);
		listOfComments.setVisible(true);
		scrollPane.setViewportView(listOfComments);
		
		like = new JButton("Like "+String.valueOf(((OutfitReview)model).getOutfit().getNumberOfLikes()));
		like.setBounds(10, 170, 90, 25);
		contentPane.add(like);
		
		dislike = new JButton("Dislike "+String.valueOf(((OutfitReview)model).getOutfit().getNumberOfDislikes()));
		dislike.setBounds(10, 215, 90, 25);
		contentPane.add(dislike);
		
		comment = new JButton("Comment");
		comment.setBounds(200, 360, 90, 25);
		contentPane.add(comment);
		
		commentArea = new JEditorPane();
		commentArea.setBounds(120, 305, 200, 50);
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
	
	public String getComment()
	{
		return commentArea.getText();
	}
	
	private DefaultListModel<String> setCommentList() {
		IContainer<Comment> commentList = ((OutfitReview) model).getOutfit().getComments();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Comment comment : commentList.getContainer()) {
			
			String text = "<html>" + comment.getUserName() + " : " + comment.getComment() + "</html>";
			listModel.addElement(text);
		}
		return listModel;
	}
	
	@Override
	public void update(Observable observable, Object args) {
		if(args instanceof ColorResult){
			like.setBackground(((ColorResult)args).getLikeButtonColor());
			dislike.setBackground(((ColorResult)args).getDislikeButtonColor());
			like.setText("Like "+String.valueOf(((OutfitReview)model).getOutfit().getNumberOfLikes()));
			dislike.setText("Dislike "+String.valueOf(((OutfitReview)model).getOutfit().getNumberOfDislikes()));
		}
		if(args.equals("updateList")){
			listOfComments.setModel(setCommentList());
		}
		
		
	}
}
