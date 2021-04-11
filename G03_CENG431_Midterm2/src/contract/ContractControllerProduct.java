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
	// takes user name
	public Product getContracterOfContractee(String contractee) throws ItemNotFoundException {
		Product found = null;
		for (Contract contract : getContracts()) {
			String userName = ((User) contract.getContractee()).getUserName();
			if (userName.equals(contractee)) { // if user name equals given user name
				found = (Product) contract.getContracter(); // take signer
				break;
			}
		}
		if (found == null) {// if not found
			throw new ItemNotFoundException("The user " + contractee + " has no product.");
		} else {
			return found;
		}
	}

	@Override
	// takes product name
	public User getContracteeOfContracter(String contracter) throws ItemNotFoundException {
		User found = null;
		for (Contract contract : getContracts()) {
			String prdId = ((Product) contract.getContracter()).getId();
			if (prdId.equals(contracter)) {// if id equals temp id
				found = (User) contract.getContractee();// make it found
				break;
			}
		}
		if (found == null) {// if not found
			throw new ItemNotFoundException("The product " + contracter + " has no user.");
		} else {
			return found;
		}
	}

}
