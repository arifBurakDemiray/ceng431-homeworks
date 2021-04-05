package factory;

import java.util.ArrayList;
import java.util.Collection;

public class Validator {
	
	private Collection<String> idContainer;
	
	public Validator() {
		idContainer = new ArrayList<String>();
	}
	
	public ValidationResult validateProduct(String id,String type) {
		boolean isNotNull = !id.equals(null) || !type.equals(null);
		if(!isNotNull)
			return new ValidationResult(false,"Missing attributes.");
		boolean uniqueId = idContainer.add(id);
		boolean isIdInteger = Integer.valueOf(id) instanceof Integer;
		ValidationResult vr = validateType(type);
		boolean isValidType = vr.isValid();
		boolean result = isIdInteger && isValidType && uniqueId && isNotNull;
		String msg = "Product is not valid to create. ";
		if(!isValidType) msg+=vr.message;
		return new ValidationResult(result,msg);
	}
	
	private ValidationResult validateType(String type) {
		boolean isPart = type.equals("Part");
		boolean isAssembly = type.equals("Assembly");
		boolean result = isPart || isAssembly;
		return new ValidationResult(result,"Product type is invalid.");
	}
	
	protected ValidationResult validateState(String state) {
		boolean notStarted = state.equals("NotStarted");
		boolean inProgress = state.equals("InProgress");
		boolean completed = state.equals("Completed");
		boolean result = notStarted || inProgress || completed;
		return new ValidationResult(result,"Product's state is invalid.");
	}
	
}
