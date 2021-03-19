/**
 * 
 */
package exception;

public class UnauthorizedUserOperationException extends Exception {

	private static final long serialVersionUID = -6005204757945142905L;

	public UnauthorizedUserOperationException() {
		super("UnauthorizedUserOperationException, this user is not authorized to do that operation.");

	}

	public UnauthorizedUserOperationException(String message) {
		super(message);
	}

	public UnauthorizedUserOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedUserOperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnauthorizedUserOperationException(Throwable cause) {
		super(cause);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
