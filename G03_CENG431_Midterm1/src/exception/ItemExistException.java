package exception;

public class ItemExistException extends Exception {

	private static final long serialVersionUID = 8062612492147243742L;

	public ItemExistException() {
		super("ItemExistException, that type of item exists.");
	}

	public ItemExistException(String message) {
		super(message);
	}

	public ItemExistException(Throwable cause) {
		super(cause);
	}

	public ItemExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
