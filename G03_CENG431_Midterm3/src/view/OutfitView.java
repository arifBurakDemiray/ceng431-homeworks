package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import model.Outfit;
import observation.Observable;
import observation.Observer;

public class OutfitView implements Observer {
	protected Outfit model;
	protected TextField display;
	protected Button upButton;
	protected JButton button;
	protected JButton dislike;
	private int numberOfLikes;
	private int nod;
	private boolean disliked = false;
	private boolean liked = false;
	public OutfitView(String label, Outfit model, int h, int v) {
		this.model = model;
		model.addObserver(this);
		JFrame window = new JFrame("Outfigram");
		this.numberOfLikes = model.getNumberOfLikes();

		//window.setLocationRelativeTo(null);
		button = new JButton("Like "+String.valueOf(numberOfLikes));
		button.setBounds(50,50, 80,30);
		button.addActionListener(new LikeButtonListener());
		window.add(button, 0);
		

		this.nod = model.getNumberOfDislikes();
		dislike = new JButton("Dislike "+String.valueOf(nod));
		dislike.setBounds(50,90, 150,30);		
		dislike.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!disliked) 
					model.increaseDislike();
				dislike.setBackground(Color.RED);
				button.setBackground(Color.WHITE);
				disliked=true;
				if(liked) {
					model.decreaseLike();
					liked=false;}
			}
		});
		window.add(dislike, 0);
		window.setSize(400,400);  
		window.setLayout(null);//using no layout manager  
		window.setVisible(true);  
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		//window.setVisible(true);
		
	}

	protected Outfit model() {
		return model;
	}

	public static class CloseListener extends WindowAdapter { // close all related windows
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

	@Override
	public void update(Observable observable, Object args) {
		this.numberOfLikes = ((Outfit)observable).getNumberOfLikes();
		this.nod = ((Outfit)observable).getNumberOfDislikes();
		this.button.setText("Like "+String.valueOf(numberOfLikes));
		this.dislike.setText("Dislike "+String.valueOf(nod));

	}

}
