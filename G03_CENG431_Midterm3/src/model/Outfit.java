package model;

import storage.CommentContainer;
import storage.IContainer;

public class Outfit {

	private String id;
	private String brandName;
	private String type;
	private String gender;
	private String size;
	private String color;
	private int numberOfLikes;
	private int numberOfDislikes;
	private IContainer<Comment> comments;
	 
	public Outfit(String id,String brandName, String type, String gender, String size, String color){
		this.id = id;	
		this.brandName = brandName;
		this.type = type;
		this.gender = gender;
		this.size = size;
		this.color = color;
		this.comments = new CommentContainer();
	}
	
	public Outfit(String id,String name, String brandName, String type, String gender, String size, String color,int numberOfLikes, int numberOfDislikes, IContainer<Comment> comments){
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

	public String getId() {
		return id;
	}



	public String getBrandName() {
		return brandName;
	}

	public String getType() {
		return type;
	}

	public String getGender() {
		return gender;
	}

	public String getSize() {
		return size;
	}

	public String getColor() {
		return color;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public int getNumberOfDisliked() {
		return numberOfDislikes;
	}

	public IContainer<Comment> getComments() {
		return comments;
	}
	
	public boolean addComment(Comment comment){
		return this.comments.add(comment);
	}
	
	public void increaseLike(){
		this.numberOfLikes++;
	}
	
	public void increaseDislike(){
		this.numberOfDislikes++;
	}
	
	public void decreaseLike(){
		this.numberOfLikes--;
	}
	
	public void decreaseDislike(){
		this.numberOfDislikes--;
	}
	
	public boolean equals(String id){
		return this.getId().equals(id);
	}

}
