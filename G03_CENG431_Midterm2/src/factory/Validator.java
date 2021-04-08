package factory;

import java.util.Collection;
import java.util.HashSet;

import product.Product;
import storage.IContainer;
import user.Employee;
import user.Manager;
import user.User;

public class Validator {

	private Collection<String> idContainer;
	private Collection<String> nameContainer;

	public Validator() {
		idContainer = new HashSet<String>();
		nameContainer = new HashSet<String>();
	}

	public ValidationResult validateProduct(String id, String type) {

		boolean isNotNull = !type.equals(null);
		if (!isNotNull)
			return new ValidationResult(false, "Missing attributes.");
		ValidationResult vrProductType = validateProductType(type);
		boolean isValidType = vrProductType.isValid();
		boolean result = isValidType && isNotNull;
		String msg = "Product is not valid to create. ";
		if (!isValidType)
			idContainer.remove(id);
		msg += vrProductType.message;
		return new ValidationResult(result, msg);
	}

	private ValidationResult validateProductType(String type) {
		boolean isPart = type.equals("Part");
		boolean isAssembly = type.equals("Assembly");
		boolean result = isPart || isAssembly;
		return new ValidationResult(result, "Product type is invalid.");
	}

	protected ValidationResult validateState(String state) {
		boolean notStarted = state.equals("NotStarted");
		boolean inProgress = state.equals("InProgress");
		boolean completed = state.equals("Completed");
		boolean result = notStarted || inProgress || completed;
		return new ValidationResult(result, "Product's state is invalid.");
	}

	protected ValidationResult validateId(String id) {
		boolean uniqueId = idContainer.add(id);
		boolean isIdInteger = Integer.valueOf(id) instanceof Integer;
		boolean result = uniqueId && isIdInteger;
		return new ValidationResult(result, "Id is invalid.");
	}

	public ValidationResult validateUser(String name, String type, String password) {
		boolean isNotNull = !name.equals(null) || !password.equals(null) || !type.equals(null);
		if (!isNotNull)
			return new ValidationResult(false, "Missing attributes.");

		boolean uniqueName = nameContainer.add(name); // name container
		ValidationResult vrUserType = validateUserType(type);
		ValidationResult vrPassword = validatePassword(password);
		boolean isValidType = vrUserType.isValid();
		boolean isValidPassword = vrPassword.isValid();
		boolean result = isValidType && uniqueName && isNotNull && isValidPassword;
		String msg = "User is not valid to create. ";
		if (!isValidType) {
			nameContainer.remove(name);
			msg += vrUserType.message;
		}
		if (!isValidPassword) {
			nameContainer.remove(name);
			msg += vrPassword.message;
		}
		if (!uniqueName)
			msg += "User name has already exists";
		return new ValidationResult(result, msg);
	}

	private ValidationResult validateUserType(String type) {
		boolean isManager = type.equals("Manager");
		boolean isEmployee = type.equals("Employee");
		boolean result = isManager || isEmployee;
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
			users.getByName(userName);
			products.getById(productId);
			returnedResult = new ValidationResult(true, "Contract validation error. ");
		} catch (Exception e) {
			returnedResult = new ValidationResult(false, "Contract validation : getBy error.");
		}

		return returnedResult;

	}

	public ValidationResult validateContractEmployee(String managerName, String[] userId, IContainer<User> users) {
		ValidationResult returnedResult = null;
		try {
			User manager = users.getByName(managerName);
			if (!(manager instanceof Manager))
				returnedResult = new ValidationResult(false, "Contract validation: user is no t a manager");
			else {
				for (String user : userId) {
					User employee = users.getByName(user);
					if (!(employee instanceof Employee)) {
						returnedResult = new ValidationResult(false, "Contract validation: user is not an employee. ");
						break;
					}
				}

				returnedResult = new ValidationResult(true, "Contract validation error. ");
			}
		} catch (Exception e) {
			returnedResult = new ValidationResult(false, "Contract validation : getBy error.");
		}

		return returnedResult;

	}

}
