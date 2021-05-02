package service;

import java.awt.Color;

import javax.swing.DefaultListModel;

import fileio.DislikeContractRepository;
import fileio.LikeContractRepository;
import fileio.OutfitRepository;
import model.ColorResult;
import model.Comment;
import model.LikeResult;
import model.OutfitReview;
import observation.Observable;
import storage.IContainer;

/**
 * This class handles requests from controller
 */
public class OutfitPopupService {

	private final OutfitRepository outfitRepository;
	private final DislikeContractRepository dislikeRepository;
	private final LikeContractRepository likeRepository;

	public OutfitPopupService() {
		outfitRepository = new OutfitRepository();
		likeRepository = new LikeContractRepository();
		dislikeRepository = new DislikeContractRepository();
	}

	/**
	 * This function simply increases a outfit's like
	 * 
	 * @param model that is outfit review has user and outfit
	 */
	public void increaseLike(Observable model) {
		if (model instanceof OutfitReview) {
			OutfitReview review = ((OutfitReview) model);
			review.increaseLike(); // increase like and add like information to repository
			likeRepository.addLikedOutfit(review.getUser().getUserName(), review.getOutfit().getId());
			likeRepository.saveChanges();
		}

	}

	/**
	 * This function simply decreases a outfit's dislike
	 * 
	 * @param model that is outfit review has user and outfit
	 */
	public void decreaseDislike(Observable model) {
		if (model instanceof OutfitReview) {
			OutfitReview review = ((OutfitReview) model);
			review.decreaseDislike(); // decrease dislike and remove dislike information from repository
			dislikeRepository.removeDislikedOutfit(review.getUser().getUserName(), review.getOutfit().getId());
			dislikeRepository.saveChanges();
		}

	}

	/**
	 * This function simply decreases a outfit's like
	 * 
	 * @param model that is outfit review has user and outfit
	 */
	public void decreaseLike(Observable model) {
		if (model instanceof OutfitReview) {
			OutfitReview review = ((OutfitReview) model);
			review.decreaseLike();// decrease like and remove like information from repository
			likeRepository.removeLikedOutfit(review.getUser().getUserName(), review.getOutfit().getId());
			likeRepository.saveChanges();
		}

	}

	/**
	 * This function simply increases a outfit's dislike
	 * 
	 * @param model that is outfit review has user and outfit
	 */
	public void increaseDislike(Observable model) {
		if (model instanceof OutfitReview) {
			OutfitReview review = ((OutfitReview) model);
			review.increaseDislike(); // increase dislike and add dislike information to repository
			dislikeRepository.addDislikedOutfit(review.getUser().getUserName(), review.getOutfit().getId());
			dislikeRepository.saveChanges();
		}

	}

	/**
	 * This function adds comment to outfit
	 * 
	 * @param commentText that is comment
	 * @param model       that is outfit review has user and outfit information
	 */
	public void addComment(String commentText, Observable model) {
		String userName = ((OutfitReview) model).getUser().getUserName(); // take user name
		Comment comment = new Comment(userName, commentText);// create new comment model
		((OutfitReview) model).addComment(comment); // and add comment to outfit
		outfitRepository.saveChanges();
	}

	/**
	 * This function notifies observer about button colors
	 * 
	 * @param model  that is has notified observer
	 * @param result that has color informations
	 */
	public void notifyOutfitPopupView(Observable model, LikeResult result) {
		Color likeButton;
		Color dislikeButton;
		if (result.isLiked()) { // if like button pressed
			likeButton = Color.GREEN;
			dislikeButton = Color.WHITE;
		} else if (result.isDisliked()) { // if dislike button pressed
			dislikeButton = Color.RED;
			likeButton = Color.WHITE;
		} else { // if none of them pressed
			dislikeButton = Color.WHITE;
			likeButton = Color.WHITE;
		}
		// send color infos to observer
		model.setAndNotify(new ColorResult(likeButton, dislikeButton));
	}

	/**
	 * This function sends initial color infos to observer
	 * 
	 * @param model that has observer
	 * @return LikeResult that has color infos
	 */
	public LikeResult setInitialButtonColor(Observable model) {
		OutfitReview review = ((OutfitReview) model);
		// look user has like about outfit
		boolean isLiked = likeRepository.userHasLiked(review.getUser().getUserName(), review.getOutfit().getId());
		// look user has dislike about outfit
		boolean isDisliked = dislikeRepository.userHasDisliked(review.getUser().getUserName(),
				review.getOutfit().getId());
		return new LikeResult(isLiked, isDisliked); // and return results
	}

	/**
	 * This function simply sets up comment list for JList
	 * 
	 * @param model that has observer
	 * @return rendered comment list
	 */
	public DefaultListModel<String> setCommentList(Observable model) {
		IContainer<Comment> commentList = ((OutfitReview) model).getOutfit().getComments();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Comment comment : commentList) {

			String text = "<html>" + comment.getUserName() + " : " + comment.getComment() + "</html>";
			listModel.addElement(text);
		}
		return listModel;
	}

}
