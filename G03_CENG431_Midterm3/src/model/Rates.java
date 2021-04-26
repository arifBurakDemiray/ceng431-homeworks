package model;

import observation.Observable;

public class Rates extends Observable {

	public String topFollewedUserName;
	public String topLikedOutfitType;
	public String topDislikedOutfitType;
	
	public Rates(String userName, String likedOutfit, String dislikedOutfit) {
		this.topDislikedOutfitType=dislikedOutfit;
		this.topFollewedUserName=userName;
		this.topLikedOutfitType=likedOutfit;
	}
}
