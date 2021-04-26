package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Outfit;
import observation.Observable;
import observation.Observer;
import view.OutfitView;

public class OutfitController {

	private boolean liked = false;
	private boolean disliked = false;
	private Outfit model;
	private OutfitView view;

	public OutfitController(Observable model, Observer view) {
		this.model = (Outfit) model;
		this.view = (OutfitView) view;
		this.view.addDislikeListener(new DislikeButtonListener());
		this.view.addLikeListener(new LikeButtonListener());
	}

	class LikeButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!liked)
				model.increaseLike();

			liked = true;
			if (disliked) {
				model.decreaseDislike();
				disliked = false;
			}
		}
	}

	class DislikeButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!disliked)
				model.increaseDislike();
			disliked = true;
			if (liked) {
				model.decreaseLike();
				liked = false;
			}
			view.updateButtonColors(liked);
		}
	}
}
