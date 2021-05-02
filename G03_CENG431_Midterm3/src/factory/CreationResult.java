package factory;

/**
 * This class is the result of the creator
 */
public class CreationResult {

	public Object object; // created result
	public String message; // created message

	public CreationResult(Object obj, String msg) {
		this.object = obj;
		this.message = msg;
	}
}
