package factory;


public class CreationResult {
	
	public Object object;
	public String message;
	public CreationResult(Object obj,String msg) {
		this.object = obj;
		this.message = msg;
	}
}
