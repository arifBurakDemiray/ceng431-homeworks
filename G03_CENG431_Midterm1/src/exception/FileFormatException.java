package exception;

public class FileFormatException extends Exception {

	private static final long serialVersionUID = 2540916260158001137L;

	public FileFormatException() {
		super("FileFormatException, file is in wrong format to read.");

	}

	public FileFormatException(String message) {
		super(message);
	}

	public FileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileFormatException(Throwable cause) {
		super(cause);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
