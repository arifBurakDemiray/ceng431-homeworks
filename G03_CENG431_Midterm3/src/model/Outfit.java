package model;

import observation.Observable;
import storage.CommentContainer;
import storage.IContainer;

public class Outfit extends Observable {

	private String id; // outfit id
	private String brandName; // outfit brand name
	private String type; // outfit type
	private String gender; // outfit gender
	private String[] size; // outfit sizes
	private String color; // outfit color
	private int numberOfLikes; // like number of outfit
	private int numberOfDislikes; // dislike number of outfit
	private IContainer<Comment> comments;

	/**
	 * The model for the Outfit
	 *
	 */
	public Outfit(String id, String brandName, String type, String gender, String[] size, String color) {
		this(id, brandName, type, gender, size, color, 0, 0, new CommentContainer());
	}

	public Outfit(String id, String brandName, String type, String gender, String[] size, String color,
			int numberOfLikes, int numberOfDislikes) {
		this(id, brandName, type, gender, size, color, numberOfLikes, numberOfDislikes, new CommentContainer());
	}

	public Outfit(String id, String brandName, String type, String gender, String[] size, String color,
			int numberOfLikes, int numberOfDislikes, IContainer<Comment> comments) {
		this.id = id;
		this.brandName = brandName;
		this.type = type;
		this.gender = gender;
		this.size = size;
		this.color = color;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
		this.comments = comments;
	}

	/**
	 * Returns the id of outfit
	 * 
	 * @return id of outfit
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the brand name of outfit
	 * 
	 * @return brand name of outfit
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * Returns the type of outfit
	 * 
	 * @return type of outfit
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the gender of outfit
	 * 
	 * @return gender of outfit
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Returns the sizes of outfit
	 * 
	 * @return sizes of outfit
	 */
	public String[] getSize() {
		return size;
	}

	/**
	 * Returns the color of outfit
	 * 
	 * @return color of outfit
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Returns the like number of outfit
	 * 
	 * @return like number of outfit
	 */
	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	/**
	 * Returns the dislike number of outfit
	 * 
	 * @return dislike number of outfit
	 */
	public int getNumberOfDislikes() {
		return numberOfDislikes;
	}

	/**
	 * Returns the comments of outfit
	 * 
	 * @return comment container
	 */
	public IContainer<Comment> getComments() {
		return comments;
	}

	/**
	 * The function sets the like number
	 * 
	 * @param numberOfLikes
	 */
	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	/**
	 * The function sets the dislike number
	 * 
	 * @param numberOfLikes
	 */
	public void setNumberOfDislikes(int numberOfDislikes) {
		this.numberOfDislikes = numberOfDislikes;
	}

	/**
	 * The function controls that given id is equal to that outfit's id
	 * 
	 * @param id = given id
	 * @return boolean
	 */
	public boolean equals(String id) {
		return this.getId().equals(id);
	}

	/**
	 * The function is returns a string of outfit with json type for outfits.xml
	 * 
	 * @return string of outfit
	 */
	public String toString() {
		return "\"" + this.getId() + "\":{\"brand_name\":\"" + this.getBrandName() + "\",\"gender\":\""
				+ this.getGender() + "\",\"noflikes\":\"" + this.getNumberOfLikes() + "\",\"nofdislikes\":\""
				+ this.getNumberOfDislikes() + "\",\"type\":\"" + this.getType() + "\",\"size\":\""
				+ String.join(",", this.getSize()) + "\",\"color\":\"" + this.getColor() + "\",\"comments\""
				+ this.getComments().toString() + "}";
	}

}
