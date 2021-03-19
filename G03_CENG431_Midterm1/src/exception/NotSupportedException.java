package exception;

public class NotSupportedException extends Exception {

	private static final long serialVersionUID = 1394184048866781363L;

	public NotSupportedException() {
		super("NotSupportedException, this function is not supported by this class.");

	}

	public NotSupportedException(String message) {
		super(message);
	}

	public NotSupportedException(Throwable cause) {
		super(cause);
	}

	public NotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotSupportedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
