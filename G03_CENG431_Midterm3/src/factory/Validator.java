package factory;

import java.util.Collection;
import java.util.HashSet;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Outfit;
import model.User;
import storage.IContainer;

public class Validator implements IValidatorService {

	// unique collections
	private Collection<String> idContainer;
	private Collection<String> nameContainer;

	// Only can be used by Creator
	protected Validator() {
		idContainer = new HashSet<String>();
		nameContainer = new HashSet<String>();
	}

	/**
	 * The function tries to validate gotten parameters, after validation returns
	 * the result.
	 */
	public ValidationResult validateOutfit(String brand_name, String gender, String type, String size, String color,
			String nofLikes, String nofDislikes) {

		// control that gotten params are not null
		boolean isNotNull = !brand_name.equals(null) || !gender.equals(null) || !type.equals(null) || !size.equals(null)
				|| !color.equals(null);

		if (!isNotNull) // if params contain null value
			return new ValidationResult(false, "Missing attributes.");

		String msg = "Product is not valid to create. "; // last message

		// Control that gotten like/dislike numbers are greater than or equal to zero.
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

		boolean result = isNotNull && isLikesNumber && isDislikesNumber; // result of like/dislike control
		return new ValidationResult(result, msg); // return result with result message
	}

	/**
	 * The function validates the outfit id. If id is not unique id, create a new id
	 * in the Creator's method.
	 */
	public ValidationResult validateId(String id) {
		boolean uniqueId = idContainer.add(id);
		boolean isIdInteger = Integer.valueOf(id) instanceof Integer;
		boolean result = uniqueId && isIdInteger;// id must be integer or can be added to unique ids
		return new ValidationResult(result, "Id is invalid.");
	}

	/**
	 * The function validates the user name and password.
	 */
	public ValidationResult validateUser(String name, String password) {
		boolean isNotNull = !name.equals(null) || !password.equals(null);
		if (!isNotNull) // if one of them is null
			return new ValidationResult(false, "Missing attributes.");

		boolean uniqueName = nameContainer.add(name); // control that user name is unique or not

		ValidationResult vrPassword = validatePassword(password); // validate password according to password rules

		// According the controls, return the result
		boolean isValidPassword = vrPassword.isValid();
		boolean result = uniqueName && isNotNull && isValidPassword; // initialise result
		String msg = "User is not valid to create. ";// initialise message
		if (!isValidPassword) {// if not a valid password, add message and remove unique name from name
								// container
			nameContainer.remove(name);
			msg += vrPassword.message;
		}
		if (!uniqueName)// if is not a unique name, add message.
			msg += "User name has already exists";
		return new ValidationResult(result, msg);// return result
	}

	/**
	 * The function validates the password
	 * 
	 * @param password
	 * @return validation result
	 */
	private ValidationResult validatePassword(String password) {
		boolean result = password.length() >= 6;
		return new ValidationResult(result, "Password's length must be above 5. ");
	}

	/**
	 * The function validates the gotten outfit id is exists or not.
	 */
	public ValidationResult validateContractUserOutfitsLikes(String outfitId, IContainer<Outfit> outfits) {

		String message = "There is no outfit with id " + outfitId;
		boolean isValid = false;
		try {
			outfits.getById(outfitId); // try to find outfit of id in the outfit container.
			isValid = true;
		} catch (ItemNotFoundException | NotSupportedException e) {
		}

		return new ValidationResult(isValid, message);
	}

	/**
	 * The function validates the gotten user name is exists or not.
	 */
	public ValidationResult validateUsername(String username, IContainer<User> users) {

		String message = "There is no user named " + username;
		boolean isValid = false;
		try {
			users.getByName(username); // try to find user name in the user container.
			isValid = true;
		} catch (ItemNotFoundException | NotSupportedException e) {
		}

		return new ValidationResult(isValid, message);

	}

}
