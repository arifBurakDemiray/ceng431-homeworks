package factory;

/**
 * This class is the result of validation process
 */
public class ValidationResult {

	private boolean isValid; // validity boolean
	protected String message; // messages

	protected ValidationResult(boolean validity, String msg) {
		this.message = msg;
		this.isValid = validity;
	}

	protected boolean isValid() {
		return this.isValid;
	}

}
