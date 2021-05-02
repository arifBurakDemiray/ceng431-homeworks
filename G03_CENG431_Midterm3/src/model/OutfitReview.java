package model;

import observation.Observable;

public class OutfitReview extends Observable {
	private Outfit outfit; // selected outfit in home view
	private User user; // logged in user

	/**
	 * The model for the OutfitReview which holds selected outfit in home view and
	 * holds logged in user
	 *
	 */
	public OutfitReview(Outfit outfit, User user) {
		this.outfit = outfit;
		this.user = user;
	}

	/**
	 * Returns selected outfit
	 * 
	 * @return Outfit
	 */
	public Outfit getOutfit() {
		return outfit;
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
	 * The function adds the gotten comment to the outfit's comment container
	 * 
	 * @param comment = gotten comment object
	 * @return result of add process
	 */
	public boolean addComment(Comment comment) {
		return outfit.getComments().add(comment);
	}

	/**
	 * The function increases the like number of the outfit
	 */
	public void increaseLike() {
		outfit.setNumberOfLikes(outfit.getNumberOfLikes() + 1);
	}

	/**
	 * The function increases the dislike number of the outfit
	 */
	public void increaseDislike() {
		outfit.setNumberOfDislikes(outfit.getNumberOfDislikes() + 1);
	}

	/**
	 * The function decreases the like number of the outfit ( when undo of liking )
	 */
	public void decreaseLike() {
		outfit.setNumberOfLikes(outfit.getNumberOfLikes() - 1);
	}

	/**
	 * The function decreases the dislike number of the outfit ( when undo of
	 * disliking )
	 */
	public void decreaseDislike() {
		outfit.setNumberOfDislikes(outfit.getNumberOfDislikes() - 1);
	}

	/**
	 * The function returns a string of outfit to show information of outfit in the
	 * view
	 * 
	 * @return string of outfit for view
	 */
	public String toString() {

		return "<html>" + "Type : " + outfit.getType() + "<br/>" + "Brand Name : " + outfit.getBrandName() + "<br/>" + "Gender : " + outfit.getGender() + "<br/>"
				+ "Sizes : " + String.join("-", outfit.getSize()) + "</html>";
	}

}
