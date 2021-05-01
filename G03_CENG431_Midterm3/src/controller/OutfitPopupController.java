package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.LikeResult;
import model.UpdatedList;
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

	public OutfitPopupController(Observable Model, Observer View) {
		this.view = View;
		this.model = Model;
		service = new OutfitPopupService();
		model.addObserver(View);
		initializeReviewButtonColors();		
		((OutfitPopupView) view).AddLikeListener(new LikeButtonListener());
		((OutfitPopupView) view).AddDislikeListener(new DislikeButtonListener());
		((OutfitPopupView) view).AddCommentListener(new CommentButtonListener());
		updateCommentList();
	}

	private void initializeReviewButtonColors(){
		LikeResult lr = service.setInitialButtonColor(model);
		liked = lr.isLiked();
		disliked = lr.isDisliked();
		service.notifyOutfitPopupView(model, lr);
	}

	class CommentButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String comment = ((OutfitPopupView) view).getComment();
			if (comment != null && !comment.equals("")) {
				service.addComment(comment, model);
				updateCommentList();
				
			}
		}
	}

	class LikeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!liked) {
				service.increaseLike(model);

				liked = true;
				if (disliked) {
					service.decreaseDislike(model);
					disliked = false;
				}
			} else {
				liked = false;
				service.decreaseLike(model);
			}
			service.notifyOutfitPopupView(model, new LikeResult(liked, disliked));
		}

	}

	class DislikeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!disliked) {
				service.increaseDislike(model);
				disliked = true;
				if (liked) {
					service.decreaseLike(model);
					liked = false;
				}
			} else {
				disliked = false;
				service.decreaseDislike(model);
			}
			service.notifyOutfitPopupView(model, new LikeResult(liked, disliked));
		}
	}
	
	private void updateCommentList(){
		model.setAndNotify(new UpdatedList(service.setCommentList(model)));
	}
}
