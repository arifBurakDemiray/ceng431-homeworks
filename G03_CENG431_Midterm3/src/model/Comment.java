package model;

public class Comment {

	private String userName;
	private String comment;
	public Comment(String userName, String comment){
		this.userName = userName;
		this.comment=comment;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getComment(){
		return this.comment;
	}
	
	public String toString(){
		return "\""+this.getUserName()+"\":\""+this.getComment()+"\",";
	}
}
