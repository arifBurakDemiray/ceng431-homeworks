package fileio;

public class DatabaseResult {

	private Object object; // result object
	private String message; // messages

	/**
	 * Database Result is the returned object of repositories
	 * 
	 * @param object
	 * @param msg
	 */
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
