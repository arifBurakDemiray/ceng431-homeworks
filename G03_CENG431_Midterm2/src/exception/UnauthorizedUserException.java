/**
 * 
 */
package exception;

public class UnauthorizedUserException extends Exception {

	private static final long serialVersionUID = -6005204757945142905L;

	public UnauthorizedUserException() {
		super("UnauthorizedUserOperationException, this user is not authorized to do that operation.");

	}

	public UnauthorizedUserException(String message) {
		super(message);
	}

	public UnauthorizedUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnauthorizedUserException(Throwable cause) {
		super(cause);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
