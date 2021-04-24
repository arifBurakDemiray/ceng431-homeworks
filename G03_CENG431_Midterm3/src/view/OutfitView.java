package view;

import java.awt.BorderLayout;
import java.awt.Button;
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
	protected Frame temperatureFrame;
	protected TextField display;
	protected Button upButton;
	protected Button downButton;
	private JLabel labelText;
	private int numberOfLikes;

	public OutfitView(String label, Outfit model, int h, int v) {
		this.model = model;
		model.addObserver(this);
		JFrame window = new JFrame("JFrame with text");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		this.numberOfLikes = model.getNumberOfLikes();
		this.labelText =new JLabel(String.valueOf(numberOfLikes));
		window.add(labelText, BorderLayout.PAGE_START);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);

		JButton button = new JButton("like");
		button.setSize(30, 30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.increaseLike();
			}
		});
		
		window.add(button, 0);

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
		this.labelText.setText(String.valueOf(numberOfLikes));

	}

}
