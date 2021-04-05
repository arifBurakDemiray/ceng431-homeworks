package factory;

import storage.IContainer;
import storage.IdContainer;

public class Validator {
	
	private IContainer<String> idContainer;
	
	public Validator() {
		idContainer = new IdContainer();
	}
	
	public boolean validateProduct(String id,String type) {
		boolean isNotNull = !id.equals(null) || !type.equals(null);
		boolean uniqueId = idContainer.add(id);
		boolean isIdInteger = Integer.valueOf(id) instanceof Integer;
		boolean isValidType = validateType(type);
		boolean result = isIdInteger && isValidType && uniqueId && isNotNull;
		return result;
	}
	
	private boolean validateType(String type) {
		boolean isPart = type.equals("Part");
		boolean isAssembly = type.equals("Assembly");
		boolean result = isPart || isAssembly;
		return result;
	}
	
	protected boolean validateState(String state) {
		boolean notStarted = state.equals("NotStarted");
		boolean inProgress = state.equals("InProgress");
		boolean completed = state.equals("Completed");
		boolean result = notStarted || inProgress || completed;
		return result;
	}
	
}
