package factory;

/**
 * This class is the result of the creator
 *
 */
public class CreationResult {

	public Object object; //create result
	public String message; //create message

	public CreationResult(Object obj, String msg) {
		this.object = obj;
		this.message = msg;
	}
}
