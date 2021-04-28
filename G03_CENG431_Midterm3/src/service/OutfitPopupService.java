package service;

import java.awt.Color;

import fileio.OutfitRepository;
import model.ColorResult;
import model.LikeResult;
import model.Outfit;
import observation.Observable;

public class OutfitPopupService {

	private final OutfitRepository or;
	public OutfitPopupService() {
		or = new OutfitRepository();
	}
	public void increaseLike(Observable model) {
		if(model instanceof Outfit) {
			((Outfit)model).increaseLike();
		}
		
	}
	public void decreaseDislike(Observable model) {
		if(model instanceof Outfit) {
			((Outfit)model).decreaseDislike();
		}
			
	}
	
	public void decreaseLike(Observable model) {
		if(model instanceof Outfit) {
			((Outfit)model).decreaseLike();
		}
			
	}
	
	public void increaseDislike(Observable model) {
		if(model instanceof Outfit) {
			((Outfit)model).increaseDislike();
		}
			
	}
	
	public void notifyOutfitPopupView(Observable model,LikeResult result) {
		Color likeButton;
		Color dislikeButton;
		if(result.isLiked())
		{
			likeButton = Color.GREEN;
			dislikeButton = Color.WHITE;
		}
		else
		{
			dislikeButton = Color.RED;
			likeButton = Color.GREEN;
		}
		model.setAndNotify(new ColorResult(likeButton,dislikeButton));
	}
	
}
