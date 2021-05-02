package model;

import observation.Observable;

public class Rates extends Observable {

	public String topFollewedUserName;
	public String topLikedOutfitType;
	public String topDislikedOutfitType;

	/**
	 * The model for the Rates which holds most liked, disliked outfit name and most
	 * followed user's name
	 *
	 */
	public Rates(String userName, String likedOutfit, String dislikedOutfit) {
		this.topDislikedOutfitType = dislikedOutfit;
		this.topFollewedUserName = userName;
		this.topLikedOutfitType = likedOutfit;
	}
}
