package model;

public class LikeResult {

	private boolean isLiked;
	private boolean isDisliked;
	
	/**
	 * The model for the LikeResult which contains the boolean for info outfit is liked or disliked
	 *
	 */
	public LikeResult(boolean valueLike,boolean valueDislike) {
		this.isLiked=valueLike;
		this.isDisliked = valueDislike;		
	}
	
	/**
	 * Returns a boolean if outfit is liked
	 * @return boolean
	 */
	public boolean isLiked() {
		return this.isLiked;
	}
	
	/**
	 * Returns a boolean if outfit is disliked
	 * @return boolean
	 */
	public boolean isDisliked() {
		return this.isDisliked;
	}
}
