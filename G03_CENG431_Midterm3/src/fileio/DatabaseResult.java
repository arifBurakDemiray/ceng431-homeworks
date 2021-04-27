package fileio;

public class DatabaseResult {

	private Object object;
	private String message; //messages

	public DatabaseResult(Object object, String msg) {
		this.object = object;
		this.message = msg;
		
	}

	public Object getObject() {
		return object;
	}

	public String getMessage() {
		return message;
	}


	
	
}
