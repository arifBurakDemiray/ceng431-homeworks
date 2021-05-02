package model;

import observation.Observable;
import storage.IContainer;

public class CollectionReview extends Observable {
	private Collection collection; // selected collection in collection view
	private User user; // logged in user

	/**
	 * The model for the CollectionReview which holds selected collection in collection view and
	 * holds logged in user
	 *
	 */
	public CollectionReview(Collection collection, User user) {
		this.collection = collection;
		this.user = user;
	}

	/**
	 * Returns selected collection
	 * 
	 * @return Collection
	 */
	public Collection getCollection() {
		return collection;
	}

	/**
	 * Returns logged in user
	 * 
	 * @return User
	 */
	public User getUser() {
		return user;
	}
	
	/**
	* Returns outfits of collection
	* @return Outfits container
	*/
	public IContainer<Outfit> getOutfits(){
		return this.collection.getOutfits();
	}



}
