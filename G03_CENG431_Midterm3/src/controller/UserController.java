package controller;

import exception.ItemNotFoundException;
import model.Collection;
import model.Outfit;
import model.User;
import observation.Observable;
import observation.Observer;
import view.UserView;

public class UserController {
	
	private User model;
	private UserView view;
	
	public UserController(Observable model, Observer view)
	{
		this.model = (User) model;
		this.view = (UserView) view;
		model.addObserver(view);
	}
	
	public void followUser(String id)
	{
		boolean isAdded = model.getFollowings().add(id);
		String printMessage = "User is followed";
		if(!isAdded)
			printMessage = "Error";
		//view.printText(printMessage);
	}
	
	public void unfollowUser(String id)
	{
		String printMessage = "User is unfollowed";
		try {
			model.getFollowings().remove(id);
			//view.printText(printMessage);
		} catch (ItemNotFoundException e) {
			printMessage = "Error";
			//view.printText(printMessage);
		}
	
	}
	
	public void createCollection()
	{
		Collection collection = null; //returnedCollection
		model.getCollections().add(collection);
	}
	
	public void addOutfit(Collection collection, Outfit outfit)
	{
		boolean isAdded = collection.getOutfits().add(outfit);
		String printMessage = "Outfit is added";
		if(!isAdded)
			printMessage = "Error";
		//view.printText(printMessage);
	}
	
	public void removeOutfit(Collection collection, Outfit outfit)
	{
		
		String printMessage = "Outfit is removed";
		try {
			collection.getOutfits().remove(outfit);
			//view.printText(printMessage);
		} catch (ItemNotFoundException e) {
			printMessage = "Error";
			//view.printText(printMessage);
		}
	
	}
	
	

}
