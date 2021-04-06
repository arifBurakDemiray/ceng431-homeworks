package factory;

import contract.Contract;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import product.Assembly;
import product.Part;
import product.Product;
import state.ProductState;
import storage.IContainer;
import state.*;
import user.*;

public class Creator {

	private final Validator validator;

	public Creator() {
		validator = new Validator();
	}

	public CreationResult createProduct(String type, String title, String id, String state) {
		ValidationResult result = validator.validateProduct(id, type);
		CreationResult cr = new CreationResult(null, "");
		Product prd = null;
		if (result.isValid()) {
			cr = createProductState(state);
			ProductState prdSt = (ProductState) cr.object;
			if (prdSt != null) {
				switch (type) {
				case "Assembly":
					prd = new Assembly(id, title, prdSt);
					break;
				case "Part":
					prd = new Part(id, title, prdSt);
					break;
				}
			}
		}
		return new CreationResult(prd, result.message + " " + cr.message);
	}

	private CreationResult createProductState(String state) {
		ValidationResult vr = validator.validateState(state);
		boolean result = vr.isValid();
		State st = null;
		ProductState pst = null;
		if (result) {
			switch (state) {
			case "NotStarted":
				st = new NotStarted();
				break;
			case "InProgress":
				st = new InProgress();
				break;
			case "Completed":
				st = new Completed();
				break;
			}
			pst = new ProductState(st);
		}
		return new CreationResult(pst, vr.message);
	}

	public CreationResult createUser(String name, String type, String password) {
		ValidationResult result = validator.validateUser(name, type, password);
		User usr = null;
		if (result.isValid()) {
			switch (type) {
			case "Manager":
				usr = new Manager(name, password);
				break;
			case "Employee":
				usr = new Employee(name, password);
				break;
			}
		}
		return new CreationResult(usr, result.message);
	}

	public CreationResult createContract(String userName, String productId, 
			IContainer<User> users,IContainer<Product> products) {
		
		ValidationResult result = validator.validateContract(userName,productId,users,products);
		Contract contract = null;
		if(result.isValid()){
			try {
				Product prd = products.getById(productId);
				User usr = users.getByName(userName);
				contract = new Contract(prd,usr);
			}  catch (NotSupportedException | ItemNotFoundException e) {
				
			}
		}
		return new CreationResult(contract,result.message);
	}
}

