package factory;

import java.util.Collection;
import java.util.HashSet;



public class Validator implements IValidatorService {

	// unique collections
	private Collection<String> idContainer;
	private Collection<String> nameContainer;

	// Only can be used by creator
	protected Validator() {
		idContainer = new HashSet<String>();
		nameContainer = new HashSet<String>();
	}

	public ValidationResult validateOutfit(String id, String type) {

		boolean isNotNull = !type.equals(null); // not null
		if (!isNotNull) // if null
			return new ValidationResult(false, "Missing attributes.");
		boolean result = isNotNull; // result
		String msg = "Product is not valid to create. ";// last message		
		return new ValidationResult(result, msg); // return result with result message
	}

	public ValidationResult validateId(String id) {
		boolean uniqueId = idContainer.add(id);
		boolean isIdInteger = Integer.valueOf(id) instanceof Integer;
		boolean result = uniqueId && isIdInteger;// id must be integer or can be added to unique ids
		return new ValidationResult(result, "Id is invalid.");
	}

	public ValidationResult validateUser(String name, String type, String password) {
		boolean isNotNull = !name.equals(null) || !password.equals(null) || !type.equals(null);
		if (!isNotNull)// one of them null
			return new ValidationResult(false, "Missing attributes.");

		boolean uniqueName = nameContainer.add(name); // name container
	
		ValidationResult vrPassword = validatePassword(password);
	
		boolean isValidPassword = vrPassword.isValid();
		boolean result = uniqueName && isNotNull && isValidPassword; // init result
		String msg = "User is not valid to create. ";// init message
		if (!isValidPassword) {// if not a valid password add message and remove unique name
			nameContainer.remove(name);
			msg += vrPassword.message;
		}
		if (!uniqueName)// if not a unique name
			msg += "User name has already exists";
		return new ValidationResult(result, msg);// return result
	}

	private ValidationResult validatePassword(String password) {
		boolean result = password.length() >= 6;
		return new ValidationResult(result, "Password's length must be above 5. ");
	}

	
}
