package contract;

import exception.ItemNotFoundException;
import product.Product;
import storage.IContainer;
import user.User;

public class ContractControllerProduct extends ContractController {

	public ContractControllerProduct(IContainer<Contract> contracts) {
		super(contracts);
	}


	@Override
	public Product getContracterOfContractee(String contractee) throws ItemNotFoundException {
		Product found = null;
		for (Contract contract : getContracts()) {
			String userName = ((User) contract.getContractee()).getUserName();
			if (userName.equals(contractee)) {
				found = (Product) contract.getContracter();
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("The user " + contractee +" has no product.");
		} else {
			return found;
		}
	}

	@Override
	public User getContracteeOfContracter(String contracter) throws ItemNotFoundException {
		User found = null;
		for (Contract contract : getContracts()) {
			String prdId = ((Product) contract.getContracter()).getId();
			if (prdId.equals(contracter)) {
				found = (User) contract.getContractee();
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("The product " + contracter +" has no user.");
		} else {
			return found;
		}
	}

}
