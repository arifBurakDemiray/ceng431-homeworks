package service;

import java.awt.Color;
import fileio.OutfitRepository;
import model.ColorResult;
import model.Comment;
import model.LikeResult;
import model.OutfitReview;
import observation.Observable;

public class OutfitPopupService {

	private final OutfitRepository or;
	public OutfitPopupService() {
		or = new OutfitRepository();
	}
	public void increaseLike(Observable model) {
		if(model instanceof OutfitReview) {
			((OutfitReview)model).increaseLike();
			or.saveChanges();
		}
		
	}
	public void decreaseDislike(Observable model) {
		if(model instanceof OutfitReview) {
			((OutfitReview)model).decreaseDislike();
			or.saveChanges();
		}
			
	}
	
	public void decreaseLike(Observable model) {
		if(model instanceof OutfitReview) {
			((OutfitReview)model).decreaseLike();
			or.saveChanges();
		}
			
	}
	
	public void increaseDislike(Observable model) {
		if(model instanceof OutfitReview) {
			((OutfitReview)model).increaseDislike();
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
	
}
