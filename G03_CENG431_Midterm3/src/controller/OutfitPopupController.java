package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.LikeResult;
import model.UpdatedList;
import observation.Observable;
import observation.Observer;
import service.OutfitPopupService;
import view.OutfitPopupView;

/**
 * This class controls outfit reviews
 */
public class OutfitPopupController extends Consumable {

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
		updateCommentList(); // Initialise comments
	}

	private void initializeReviewButtonColors() {
		LikeResult lr = service.setInitialButtonColor(model); // find initial button colors
		liked = lr.isLiked();
		disliked = lr.isDisliked();
		service.notifyOutfitPopupView(model, lr); // end send colors to service to notify observers
	}

	// if user clicks comment button
	class CommentButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String comment = ((OutfitPopupView) view).getComment();
			if (comment != null && !comment.equals("")) {// and comment is not empty
				service.addComment(comment, model); // add comment
				updateCommentList();// and update list

			}
		}
	}

	// if user clicks like button
	class LikeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!liked) { // if user not liked before, increase like
				service.increaseLike(model);

				liked = true; // and make liked true
				if (disliked) { // if user disliked before
					service.decreaseDislike(model); // decrease like
					disliked = false; // make disliked false
				}
			} else { // if user liked before
				liked = false; // make liked false and decrease like
				service.decreaseLike(model);
			}
			// and send service a like result to render button colors
			service.notifyOutfitPopupView(model, new LikeResult(liked, disliked));
		}

	}

	// This class is the opposite of above class
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

	// this function sends observers rendered comment list
	private void updateCommentList() {
		model.setAndNotify(new UpdatedList(service.setCommentList(model)));
	}
}
