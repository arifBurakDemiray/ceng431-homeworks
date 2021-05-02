package model;

import java.awt.Color;

public class ColorResult {

	private Color likeColor;
	private Color dislikeColor;
	
	/**
	 * The model for the ColorResult which contains color for like button and dislike button
	 *
	 */
	public ColorResult(Color like,Color dislike) {
		this.likeColor = like;
		this.dislikeColor = dislike;
	}
	
	/**
	 * Returns a color for like button
	 * @return Color
	 */
	public Color getLikeButtonColor() {
		return this.likeColor;
	}
	
	/**
	 * Returns a color for dislike button
	 * @return Color
	 */
	public Color getDislikeButtonColor() {
		return this.dislikeColor;
	}
}
