package model;

public class Comment {

	private String userName; // name of comment owner
	private String comment; // comment text

	/**
	 * The model for the Comment
	 *
	 */
	public Comment(String userName, String comment) {
		this.userName = userName;
		this.comment = comment;
	}

	/**
	 * Returns the name of comment owner
	 * 
	 * @return user name
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Returns the text of comment
	 * 
	 * @return comment text
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * The function returns a string in json format for outfits.json
	 * 
	 * @return string of comment in json type
	 */
	public String toString() {
		return "{\"" + this.getUserName() + "\":\"" + this.getComment() + "\"},";
	}
}
