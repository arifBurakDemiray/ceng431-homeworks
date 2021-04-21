package exception;

public class WrongCredentialException extends Exception {

	private static final long serialVersionUID = -3860193685789009132L;

	public WrongCredentialException() {
		super("WrongCredentialException, user info is not matched.");

	}

	public WrongCredentialException(String message) {
		super(message);
	}

	public WrongCredentialException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongCredentialException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WrongCredentialException(Throwable cause) {
		super(cause);
	}

	public String getMessage() {
		return super.getMessage();
	}

}
