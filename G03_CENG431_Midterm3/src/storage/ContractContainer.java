package storage;

import contract.Contract;
import contract.ContractUserOutfitsLikes;
import exception.ItemNotFoundException;
import exception.NotSupportedException;

public class ContractContainer extends Container<Contract> {

	/**
	 * The function throws a NotSupportedException for ContractContainer when
	 * getById() is invoked.
	 */
	@Override
	public Contract getById(String id) throws NotSupportedException {
		throw new NotSupportedException("Contract container does not supports getById() function");
	}

	/**
	 * The function throws a NotSupportedException for ContractContainer when
	 * getByName() is invoked.
	 * @throws ItemNotFoundException 
	 */
	@Override
	public Contract getByName(String name) throws ItemNotFoundException {
		Contract found = null;
		for (Contract contract : this.getContainer()) {
			if (contract.equals(name)) {
				found = contract;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no contract has user " + name);
		} else {
			return found;
		}
	}
	
	public String toString(){
		String string = "{"+super.toString();
		if (string.endsWith(",")) { // if ends with , ignore it
			string = string.substring(0, string.length() - 1);
		}
		
		return string+"}";
	}
	
	
}
