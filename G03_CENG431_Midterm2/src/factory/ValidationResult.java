package factory;

/**
 * This  class is the result of validation
 *
 */
public class ValidationResult {

	private boolean isValid; //validity
	protected String message; //messages

	protected ValidationResult(boolean validity, String msg) {
		this.message = msg;
		this.isValid = validity;
	}

	protected boolean isValid() {
		return this.isValid;
	}

}
