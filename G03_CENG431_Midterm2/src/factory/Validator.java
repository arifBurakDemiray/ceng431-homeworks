package factory;

import java.util.Collection;
import java.util.HashSet;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import product.Product;
import storage.IContainer;
import user.Employee;
import user.Manager;
import user.User;

public class Validator implements IValidatorService {

	// unique collections
	private Collection<String> idContainer;
	private Collection<String> nameContainer;

	// Only can be used by creator
	protected Validator() {
		idContainer = new HashSet<String>();
		nameContainer = new HashSet<String>();
	}

	public ValidationResult validateProduct(String id, String type) {

		boolean isNotNull = !type.equals(null); // not null
		if (!isNotNull) // if null
			return new ValidationResult(false, "Missing attributes.");
		ValidationResult vrProductType = validateProductType(type);// validate type
		boolean isValidType = vrProductType.isValid(); // valid type
		boolean result = isValidType && isNotNull; // result
		String msg = "Product is not valid to create. ";// last message
		if (!isValidType)// if type is not valid remove added id
			idContainer.remove(id);
		msg += vrProductType.message; // add message to return message
		return new ValidationResult(result, msg); // return result with result message
	}

	private ValidationResult validateProductType(String type) {
		boolean isPart = type.equals("Part");
		boolean isAssembly = type.equals("Assembly");
		boolean result = isPart || isAssembly; // types can be only part of product
		return new ValidationResult(result, "Product type is invalid.");
	}

	public ValidationResult validateState(String state) {
		boolean notStarted = state.equals("NotStarted");
		boolean inProgress = state.equals("InProgress");
		boolean completed = state.equals("Completed");// states can be only these
		boolean result = notStarted || inProgress || completed;
		return new ValidationResult(result, "Product's state is invalid.");
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
		ValidationResult vrUserType = validateUserType(type);
		ValidationResult vrPassword = validatePassword(password);
		boolean isValidType = vrUserType.isValid();
		boolean isValidPassword = vrPassword.isValid();
		boolean result = isValidType && uniqueName && isNotNull && isValidPassword; // init result
		String msg = "User is not valid to create. ";// init message
		if (!isValidType) {// if not a valid type add message and remove unique name
			nameContainer.remove(name);
			msg += vrUserType.message;
		}
		if (!isValidPassword) {// if not a valid password add message and remove unique name
			nameContainer.remove(name);
			msg += vrPassword.message;
		}
		if (!uniqueName)// if not a unique name
			msg += "User name has already exists";
		return new ValidationResult(result, msg);// return result
	}

	private ValidationResult validateUserType(String type) {
		boolean isManager = type.equals("Manager");
		boolean isEmployee = type.equals("Employee");
		boolean result = isManager || isEmployee;// a user can be manager or employee
		return new ValidationResult(result, "User type is invalid.");
	}

	private ValidationResult validatePassword(String password) {
		boolean result = password.length() >= 6;
		return new ValidationResult(result, "Password's length must be above 5. ");
	}

	public ValidationResult validateContractProduct(String userName, String productId, IContainer<User> users,
			IContainer<Product> products) {

		ValidationResult returnedResult = null;
		try {
			users.getByName(userName);// if given user name and id not exists
			products.getById(productId);// if gets gives exception not validate
			returnedResult = new ValidationResult(true, "Contract validation error. ");
		} catch (ItemNotFoundException | NotSupportedException e) {
			returnedResult = new ValidationResult(false, "Contract validation : getBy error.");
		}

		return returnedResult;

	}

	public ValidationResult validateContractEmployee(String managerName, String[] userId, IContainer<User> users) {
		ValidationResult returnedResult = null;
		try {
			User manager = users.getByName(managerName);// try to get manager name
			if (!(manager instanceof Manager))// if he is not a manager
				returnedResult = new ValidationResult(false, "Contract validation: user is not a manager");
			else {
				for (String user : userId) {
					User employee = users.getByName(user);
					if (!(employee instanceof Employee)) {// if employee is not an employee
						returnedResult = new ValidationResult(false, "Contract validation: user is not an employee. ");
						break;
					}
				}

				returnedResult = new ValidationResult(true, "Contract validation error. ");
			}
		} catch (ItemNotFoundException | NotSupportedException e) {// if given user name not found
			returnedResult = new ValidationResult(false, "Contract validation : getBy error.");
		}

		return returnedResult;

	}

}
