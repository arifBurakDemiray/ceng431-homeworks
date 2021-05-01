package service;

import java.awt.Color;

import fileio.DislikeContractRepository;
import fileio.LikeContractRepository;
import fileio.OutfitRepository;
import model.ColorResult;
import model.Comment;
import model.LikeResult;
import model.OutfitReview;
import observation.Observable;

public class OutfitPopupService {

	private final OutfitRepository or;
	private final DislikeContractRepository dcr;
	private final LikeContractRepository lcr;
	public OutfitPopupService() {
		or = new OutfitRepository();
		lcr = new LikeContractRepository();
		dcr = new DislikeContractRepository();
	}
	public void increaseLike(Observable model) {
		if(model instanceof OutfitReview) {
			OutfitReview review = ((OutfitReview)model);
			review.increaseLike();
			lcr.addLikedOutfit(review.getUser().getUserName(),review.getOutfit().getId());
			or.saveChanges();
		}
		
	}
	public void decreaseDislike(Observable model) {
		if(model instanceof OutfitReview) {
			OutfitReview review = ((OutfitReview)model);
			review.decreaseDislike();
			dcr.removeDislikedOutfit(review.getUser().getUserName(),review.getOutfit().getId());
			or.saveChanges();
		}
			
	}
	
	public void decreaseLike(Observable model) {
		if(model instanceof OutfitReview) {
			OutfitReview review = ((OutfitReview)model);
			review.decreaseLike();
			lcr.removeLikedOutfit(review.getUser().getUserName(),review.getOutfit().getId());
			or.saveChanges();
		}
			
	}
	
	public void increaseDislike(Observable model) {
		if(model instanceof OutfitReview) {
			OutfitReview review = ((OutfitReview)model);
			review.increaseDislike();
			dcr.addDislikedOutfit(review.getUser().getUserName(),review.getOutfit().getId());
			or.saveChanges();
		}
			
	}
	
	public void addComment(String commentText,Observable model)
	{
		String userName = ((OutfitReview)model).getUser().getUserName();
		Comment comment = new Comment(userName,commentText);
		((OutfitReview)model).addComment(comment);
		or.saveChanges();	
	}
	
	public void notifyOutfitPopupView(Observable model,LikeResult result) {
		Color likeButton;
		Color dislikeButton;
		if(result.isLiked())
		{
			likeButton = Color.GREEN;
			dislikeButton = Color.WHITE;
		}
		else if(result.isDisliked())
		{
			dislikeButton = Color.RED;
			likeButton = Color.WHITE;
		}
		else {
			dislikeButton = Color.WHITE;
			likeButton = Color.WHITE;
		}
		model.setAndNotify(new ColorResult(likeButton,dislikeButton));
	}
	
	public LikeResult setInitialButtonColor(Observable model){
		OutfitReview review = ((OutfitReview)model);
		boolean isLiked = lcr.userHasLiked(review.getUser().getUserName(),review.getOutfit().getId());
		boolean isDisliked = dcr.userHasDisliked(review.getUser().getUserName(),review.getOutfit().getId());
		return new LikeResult(isLiked,isDisliked);
	}
	
}
