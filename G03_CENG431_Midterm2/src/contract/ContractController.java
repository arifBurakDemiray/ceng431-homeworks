package contract;

import exception.ItemNotFoundException;
import product.Product;
import storage.IContainer;

public class ContractController {

	private IContainer<Contract> contracts;

	public ContractController(IContainer<Contract> contracts) {
		this.contracts = contracts;
	}

	public Product getUserProduct(String userName) throws ItemNotFoundException {
		Product found = null;
		for (Contract contract : this.contracts) {
			String user = contract.getUser().getUserName();
			if (user.equals(userName)) {
				found = contract.getProduct();
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("The user " + userName+" has no product.");
		} else {
			return found;
		}
	}

}
