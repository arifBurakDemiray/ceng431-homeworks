package factory;

import contract.Contract;
import contract.ContractManagerEmployee;
import contract.ContractUserProduct;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import product.Assembly;
import product.Part;
import product.Product;
import state.ProductState;
import storage.IContainer;
import storage.UserContainer;
import state.*;
import user.*;

public class Creator implements ICreatorService{

	private final IValidatorService validator;

	public Creator() {
		validator = new Validator();
	}

	// this function creates a product
	public CreationResult createProduct(String type, String title, String id, String state) {
		ValidationResult vrId; // id validation result
		
		if (id == null) {
			id = RandomFactory.randomId();
			vrId = validator.validateId(id);
			while (!vrId.isValid()) {// if id null create a random id until it is valid
				id = RandomFactory.randomId();
				vrId = validator.validateId(id); // and validate it
			}
		} else { // if not null
			vrId = validator.validateId(id);
		}
		ValidationResult result = validator.validateProduct(id, type);// validate product
		String resultMessage="";
		String vrIdMessage="";
		if(!vrId.isValid())
		{
			vrIdMessage= vrId.message;
		}
		if(!result.isValid())
		{
			resultMessage = result.message;
		}
		CreationResult cr = new CreationResult(null, "");// creation result for product type
		Product prd = null;
		if (result.isValid() && vrId.isValid()) {
			cr = createProductState(state);
			ProductState prdSt = (ProductState) cr.object;
			if (prdSt != null) {// if product state is not null
				switch (type) {
				case "Assembly":// create them
					prd = new Assembly(id, title, prdSt);
					break;
				case "Part":
					prd = new Part(id, title, prdSt);
					break;
				}
			}
		} // return newly created product
		return new CreationResult(prd, resultMessage + " " + cr.message + " " + vrIdMessage);
	}

	private CreationResult createProductState(String state) {
		State st = null;// this function creates a product state
		ProductState pst = null;
		String msg = "";
		if (state != null) {
			ValidationResult vr = validator.validateState(state);
			boolean result = vr.isValid();
			if (result) { // if state validation is valid
				switch (state) {
				case "NotStarted":
					st = new NotStarted();
					pst = new ProductState(st);
					break;
				case "InProgress":
					st = new InProgress();
					pst = new ProductState(st);
					break;
				case "Completed":
					st = new Completed();
					pst = new ProductState(st);
					break;
				}
				msg = vr.message;
			}
		} else
			pst = new ProductState();
		return new CreationResult(pst, msg);
	}

	// this function creates an user
	public CreationResult createUser(String name, String type, String password) {
		ValidationResult result = validator.validateUser(name, type, password); // validate inputs
		User usr = null;
		if (result.isValid()) {// if valid create user
			switch (type) {
			case "Manager":
				usr = new Manager(name, password);
				break;
			case "Employee":
				usr = new Employee(name, password);
				break;
			}
		} // return result
		return new CreationResult(usr, result.message);
	}

	// create user-product contract
	public CreationResult createContractUserProduct(String userName, String productId, IContainer<User> users,
			IContainer<Product> products) {

		// validate inputs
		ValidationResult result = validator.validateContractProduct(userName, productId, users, products);
		Contract contract = null;
		if (result.isValid()) {// if is valid
			try {
				Product prd = products.getById(productId);// try to get them
				User usr = users.getByName(userName);
				contract = new ContractUserProduct(usr, prd);
			} catch (NotSupportedException | ItemNotFoundException e) {

			}
		} // return result
		return new CreationResult(contract, result.message);
	}

	// this function creates manager-employees contract
	public CreationResult createContractManagerEmplooye(String managerName, String[] userId, IContainer<User> users) {
		// validate inputs
		ValidationResult result = validator.validateContractEmployee(managerName, userId, users);
		Contract contract = null;
		if (result.isValid()) {
			try {// create user container and add users to container
				IContainer<User> managerEmployees = new UserContainer();
				User manager = users.getByName(managerName);
				for (String user : userId) {
					User employee = users.getByName(user);
					managerEmployees.add(employee);
				}
				contract = new ContractManagerEmployee(manager, managerEmployees);
			} catch (NotSupportedException | ItemNotFoundException e) {

			}
		} // return result
		return new CreationResult(contract, result.message);
	}

}
