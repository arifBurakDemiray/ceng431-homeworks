package model;

public class LikeResult {

	private boolean isLiked;
	private boolean isDisliked;
	
	public LikeResult(boolean valueLike,boolean valueDislike) {
		this.isLiked=valueLike;
		this.isDisliked = valueDislike;
		
	}
	
	public boolean isLiked() {
		return this.isLiked;
	}
	
	public boolean isDisliked() {
		return this.isDisliked;
	}
}
