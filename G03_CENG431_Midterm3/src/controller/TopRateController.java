package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fileio.FileController;
import model.Outfit;
import model.Rates;
import model.User;
import observation.Observable;
import observation.Observer;
import storage.IContainer;
import view.TopRateView;

public class TopRateController {

	private Observer view;
	private Observable model;

	public TopRateController(Observable model, Observer view) {
		this.view = view;
		this.model = model;
		model.addObserver(view);
		((TopRateView)view).addBackButtonListener(new BackButtonListener());
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setChanged();
			model.notifyObservers("back");
			model.removeObserver(view);
		}
	}
	
	protected static Rates initializeRateModel() {
		String userName = findTopFollowedUser();
		String topLiked = findTopLikedOutfit();
		String topDisliked = findTopDislikedOutfit();
		return new Rates(userName,topLiked,topDisliked);
	}
	
	private static String findTopFollowedUser() {
		IContainer<User> users = FileController.users();
		String result = "";
		int max = 0;
		for(User user: users) {
			int followerLen = user.getFollowers().getLength();
			if(followerLen>max) {
				max = followerLen;
				result = user.getUserName();
			}
		}
		return result;
		
	}
	
	private static String findTopLikedOutfit() {
		IContainer<Outfit> outfits = FileController.outfits();
		String result = "";
		int max = 0;
		for(Outfit outfit: outfits) {
			int likes = outfit.getNumberOfLikes();
			if(likes>max) {
				max = likes;
				result = outfit.getType();
			}
		}
		return result;
		
	}

	private static String findTopDislikedOutfit() {
		IContainer<Outfit> outfits = FileController.outfits();
		String result = "";
		int max = 0;
		for(Outfit outfit: outfits) {
			int dislikes = outfit.getNumberOfDislikes();
			if(dislikes>max) {
				max = dislikes;
				result = outfit.getType();
			}
		}
		return result;
		
	}
	
	
}
