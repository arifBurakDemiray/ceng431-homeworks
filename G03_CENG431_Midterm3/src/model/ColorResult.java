package model;

import java.awt.Color;

public class ColorResult {

	private Color likeColor;
	private Color dislikeColor;
	
	public ColorResult(Color like,Color dislike) {
		this.likeColor = like;
		this.dislikeColor = dislike;
	}
	
	public Color getLikeButtonColor() {
		return this.likeColor;
	}
	
	public Color getDislikeButtonColor() {
		return this.dislikeColor;
	}
}
