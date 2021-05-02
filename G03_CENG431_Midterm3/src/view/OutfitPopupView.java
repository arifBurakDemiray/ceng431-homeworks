package view;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.ColorResult;
import model.OutfitReview;
import model.UpdatedList;
import observation.Observable;
import observation.Observer;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JList;

/**
 * The outfit pop up view is a view which shows the selected outfit's details.
 * And user can comment, like, dislike it.
 *
 */
public class OutfitPopupView extends JFrame implements Observer {

	private static final long serialVersionUID = 8463308497742322444L;
	private Observable model;
	private JPanel contentPane;
	private JList<String> listOfComments; // JList of outfit's comments
	private JScrollPane scrollPane; // scroll pane which contains list of outfit's comments
	private JButton like; // like button
	private JButton dislike; // dislike button
	private JEditorPane commentArea; // comment writing pane
	private JLabel outfitInfo; // text of outfit's details
	private JButton comment; // add comment button

	/**
	 * Create the frame.
	 */
	public OutfitPopupView(Observable Model) {
		// set the observable and view elements in the view.
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

		// OutfitPopupController initialises list using update at the start
		// and in the continuation. Default it is empty.
		listOfComments = new JList<String>();
		listOfComments.setVisible(true);
		scrollPane.setViewportView(listOfComments);

		like = new JButton("Like " + String.valueOf(((OutfitReview) model).getOutfit().getNumberOfLikes()));
		like.setBounds(10, 170, 90, 25);
		contentPane.add(like);

		dislike = new JButton("Dislike " + String.valueOf(((OutfitReview) model).getOutfit().getNumberOfDislikes()));
		dislike.setBounds(10, 215, 90, 25);
		contentPane.add(dislike);

		comment = new JButton("Comment");
		comment.setBounds(170, 360, 90, 25);
		contentPane.add(comment);

		commentArea = new JEditorPane();
		commentArea.setBounds(120, 305, 200, 50);
		contentPane.add(commentArea);
		
		

		setVisible(true);
	}

	/**
	 * The function helps for detecting of clicking like button using listener
	 * 
	 * @param listener
	 */
	public void AddLikeListener(ActionListener listener) {
		like.addActionListener(listener);
	}

	/**
	 * The function helps for detecting of clicking dislike button using listener
	 * 
	 * @param listener
	 */
	public void AddDislikeListener(ActionListener listener) {
		dislike.addActionListener(listener);
	}

	/**
	 * The function helps for detecting of clicking add comment button using
	 * listener
	 * 
	 * @param listener
	 */
	public void AddCommentListener(ActionListener listener) {
		comment.addActionListener(listener);
	}

	/**
	 * The function returns the text of commentArea
	 * 
	 * @param String of written comment
	 */
	public String getComment() {
		return commentArea.getText();
	}

	@Override
	public void update(Observable observable, Object args) {

		// If OutfitPopupController invokes modifyAndSet() and it notifies with a
		// ColorResult model,
		// we understand that there is a change like/dislike
		// Or we understand that user liked/disliked outfit before.
		if (args instanceof ColorResult) {
			like.setBackground(((ColorResult) args).getLikeButtonColor());
			dislike.setBackground(((ColorResult) args).getDislikeButtonColor());
			like.setText("Like " + String.valueOf(((OutfitReview) model).getOutfit().getNumberOfLikes()));
			dislike.setText("Dislike " + String.valueOf(((OutfitReview) model).getOutfit().getNumberOfDislikes()));
		}

		// If OutfitPopupController invokes modifyAndSet() and it notifies with a
		// UpdatedList model,
		// we understand that there is a change in comment list
		// Or we understand that list is initialised at the start.
		// for example after commenting, it adds the comment to the
		// list and it updates the list.
		if (args instanceof UpdatedList) {
			commentArea.setText("");
			setCommentList(args);
		}

	}

	private void setCommentList(Object obj) {
		UpdatedList commentList = (UpdatedList) obj;
		listOfComments.setModel(commentList.getListModel());
	}
}
