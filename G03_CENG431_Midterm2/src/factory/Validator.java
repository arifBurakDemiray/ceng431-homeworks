package factory;

import java.util.Collection;
import java.util.HashSet;

import product.Product;
import storage.IContainer;
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
		ValidationResult vrId = validateId(id);
		if(!id.equals(null)) {
			while(!vrId.isValid()) {
				id = RandomFactory.randomId();
				vrId = validateId(id);
			}
		}
		ValidationResult vrProductType = validateProductType(type);
		boolean isValidType = vrProductType.isValid();
		boolean isValidId = vrId.isValid();
		boolean result = isValidId && isValidType && isNotNull;
		String msg = "Product is not valid to create. ";
		if (!isValidType)
			msg += vrProductType.message;
		if (!isValidId)
			msg += vrId.message;
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

	private ValidationResult validateId(String id) {
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
		if (!isValidType)
			msg += vrUserType.message;
		if (!isValidPassword)
			msg += vrPassword.message;
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
		return new ValidationResult(result, "Password's length must be above 6. ");
	}

	public ValidationResult validateContract(String userName, String productId, IContainer<User> users,
			IContainer<Product> products) {
				
			ValidationResult  returnedResult = null;
		try {
			users.getByName(userName);			
			products.getById(productId);							
			returnedResult =  new ValidationResult(true, "Contract validation error. ");
		} catch (Exception e) {
			returnedResult =  new ValidationResult(false, "Contract validation : getBy error.");
		}
		
		return returnedResult;

	}

}
