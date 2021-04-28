package model;
import observation.Observable;
public class OutfitReview extends Observable{
	private Outfit outfit;
	private User user;

	public OutfitReview(Outfit outfit, User user) {
		this.outfit = outfit;
		this.user = user;
	}

	public Outfit getOutfit() {
		return outfit;
	}

	public User getUser() {
		return user;
	}
	
	public boolean addComment(Comment comment) {
		return outfit.getComments().add(comment);
	}

	public void increaseLike() {
		outfit.setNumberOfLikes(outfit.getNumberOfLikes()+1);
		//setAndNotify();
	}

	public void increaseDislike() {
		outfit.setNumberOfDislikes(outfit.getNumberOfDislikes()+1);
		//setAndNotify();
	}

	public void decreaseLike() {
		outfit.setNumberOfLikes(outfit.getNumberOfLikes()-1);
		//setAndNotify();
	}

	public void decreaseDislike() {
		outfit.setNumberOfDislikes(outfit.getNumberOfDislikes()-1);
		//setAndNotify();
	}
	
	public String toString()
	{
		
		return "<html>"+outfit.getType()+"<br/>"+
			outfit.getBrandName()+"<br/>"+
			outfit.getGender()+"<br/>"+
			String.join("-", outfit.getSize())+"</html>";
	}

}
