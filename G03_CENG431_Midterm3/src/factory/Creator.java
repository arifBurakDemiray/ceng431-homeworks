package factory;

import model.Outfit;
import model.User;



public class Creator implements ICreatorService{

	private final IValidatorService validator;

	public Creator() {
		validator = new Validator();
	}

	// this function creates a product
	public CreationResult createOutfit(String type, String title, String id, String state) {
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
		ValidationResult result = validator.validateOutfit(id, type);// validate product
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
		Outfit prd = null;
		if (result.isValid() && vrId.isValid()) {
			
			prd = new Outfit(vrIdMessage, title, id, type, state, resultMessage, vrIdMessage, 0, 0, null);
		} // return newly created product
		return new CreationResult(prd, resultMessage + " " + cr.message + " " + vrIdMessage);
	}



	// this function creates an user
	public CreationResult createUser(String name, String type, String password) {
		ValidationResult result = validator.validateUser(name, type, password); // validate inputs
		User usr = null;
		if (result.isValid()) {// if valid create user
			usr = new User(name, password);
		} // return result
		return new CreationResult(usr, result.message);
	}


}
