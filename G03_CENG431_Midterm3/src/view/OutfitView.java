package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.Outfit;
import observation.Observable;
import observation.Observer;

public class OutfitView implements Observer {
	protected Outfit model;
	protected TextField display;
	protected Button upButton;
	protected JButton likeButton;
	protected JButton dislikeButton;
	private int numberOfLikes;
	private int numberOfDislikes;
	
	public OutfitView(String label, Outfit model, int h, int v) {
		this.model = model;
		model.addObserver(this);
		
		JFrame window = new JFrame("Outfigram");
		this.numberOfLikes = model.getNumberOfLikes();
		this.numberOfDislikes = model.getNumberOfDislikes();
		
		dislikeButton = new JButton("Dislike "+String.valueOf(numberOfDislikes));
		dislikeButton.setBounds(50,90, 150,30);	
		window.add(dislikeButton, 0);
		
		
		likeButton = new JButton("Like "+String.valueOf(numberOfLikes));
		likeButton.setBounds(50,50, 80,30);
		window.add(likeButton, 0);
		
		window.setSize(400,400);  
		window.setLayout(null);//using no layout manager  
		window.setVisible(true);  
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
	}

	public JButton getLikeButton() {
		return likeButton;
	}

	public void setLikeButton(JButton likeButton) {
		this.likeButton = likeButton;
	}

	public JButton getDislikeButton() {
		return dislikeButton;
	}

	public void setDislikeButton(JButton dislikeButton) {
		this.dislikeButton = dislikeButton;
	}

	protected Outfit model() {
		return model;
	}
	
	public void addLikeListener(ActionListener likeListener) {		
        likeButton.addActionListener(likeListener);     
    }
	
	public void addDislikeListener(ActionListener dislikeListener) {
        dislikeButton.addActionListener(dislikeListener);       
    }
	
	public void updateButtonColors(boolean isLiked)
	{
		if(isLiked)
		{
			likeButton.setBackground(Color.GREEN);
			dislikeButton.setBackground(Color.WHITE);
		}
		else
		{
			dislikeButton.setBackground(Color.RED);
			likeButton.setBackground(Color.GREEN);
		}
	}

	@Override
	public void update(Observable observable, Object args) {
		this.numberOfLikes = ((Outfit)observable).getNumberOfLikes();
		this.numberOfDislikes = ((Outfit)observable).getNumberOfDislikes();
		this.likeButton.setText("Like "+String.valueOf(numberOfLikes));
		this.dislikeButton.setText("Dislike "+String.valueOf(numberOfDislikes));
	}

}
