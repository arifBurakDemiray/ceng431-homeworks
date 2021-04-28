package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.LikeResult;
import model.Outfit;
import observation.Observable;
import observation.Observer;
import service.OutfitPopupService;
import view.OutfitPopupView;

public class OutfitPopupController {

	private OutfitPopupService service;
	private boolean liked = false;
	private boolean disliked = false;
	private Observer view;
	private Observable model;

	public OutfitPopupController(Observer View, Observable Model) {
		this.view = View;
		this.model = Model;
		model.addObserver(View);
		service = new OutfitPopupService();
		((OutfitPopupView) view).AddLikeListener(new LikeButtonListener());
		((OutfitPopupView) view).AddDislikeListener(new DislikeButtonListener());
		((OutfitPopupView) view).AddCommentListener(new CommentButtonListener());
	}

	class CommentButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class LikeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!liked)
				service.increaseLike(model);

			liked = true;
			if (disliked) {
				service.decreaseDislike(model);
				disliked = false;
			}
		}

	}

	class DislikeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!disliked)
				service.increaseLike(model);
			disliked = true;
			if (liked) {
				service.decreaseDislike(model);
				liked = false;
			}
			service.notifyOutfitPopupView(model,new LikeResult(liked));
		}
	}
}
