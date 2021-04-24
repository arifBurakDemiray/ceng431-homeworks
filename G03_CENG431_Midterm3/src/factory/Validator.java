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

	public ValidationResult validateOutfit(String brand_name, String gender, String type, String size, String color,
			String nofLikes, String nofDislikes) {

		boolean isNotNull = !brand_name.equals(null) || !gender.equals(null) || !type.equals(null) || !size.equals(null)
				|| !color.equals(null); // not null
		if (!isNotNull) // if null
			return new ValidationResult(false, "Missing attributes.");
		String msg = "Product is not valid to create. ";// last message
		boolean isLikesNumber = true;
		boolean isDislikesNumber = true;
		if (!nofLikes.isEmpty()) {
			try {
				isLikesNumber = Integer.valueOf(nofLikes) >= 0;
			} catch (NumberFormatException e) {
				isLikesNumber = false;
				msg += " Likes are not number";
			}
		}
		if (!nofLikes.isEmpty()) {
			try {
				isDislikesNumber = Integer.valueOf(nofDislikes) >= 0;
			} catch (NumberFormatException e) {
				isDislikesNumber = false;
				msg += " Dislikes are not number";
			}
		}
		boolean result = isNotNull && isLikesNumber && isDislikesNumber; // result
		return new ValidationResult(result, msg); // return result with result message
	}

	public ValidationResult validateId(String id) {
		boolean uniqueId = idContainer.add(id);
		boolean isIdInteger = Integer.valueOf(id) instanceof Integer;
		boolean result = uniqueId && isIdInteger;// id must be integer or can be added to unique ids
		return new ValidationResult(result, "Id is invalid.");
	}

	public ValidationResult validateUser(String name, String password) {
		boolean isNotNull = !name.equals(null) || !password.equals(null);
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
