package factory;

public class ValidationResult {

	private boolean isValid;
	public String message;
	public ValidationResult(boolean validity, String msg) {
		this.message = msg;
		this.isValid=validity;
	}
	public boolean isValid() {
		return this.isValid;
	}
	
}
