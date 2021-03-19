package exception;

public class ItemNotFoundException extends Exception {

	private static final long serialVersionUID = -6381514647404207755L;

	public ItemNotFoundException() {
		super("ItemNotFoundException, that type of item is not found.");

	}

	public ItemNotFoundException(String message) {
		super(message);
	}

	public ItemNotFoundException(Throwable cause) {
		super(cause);
	}

	public ItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
