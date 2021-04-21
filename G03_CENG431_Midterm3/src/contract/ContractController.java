package contract;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;

/**
 * This class handles contract operations between signer and owner
 *
 */
public abstract class ContractController {

	private IContainer<Contract> contracts; // Holds contracts

	public ContractController(IContainer<Contract> contracts) {
		this.contracts = contracts;
	}

	/**
	 * This function gets signer of the contract by owner string attribute
	 * 
	 * @param contractee
	 * @returns contracter if found
	 * @throws ItemNotFoundException for not found
	 */
	public abstract Object getContracterOfContractee(String contractee) throws ItemNotFoundException;

	/***
	 * This function gets owner of the contract by signer string attribute
	 * 
	 * @param contracter
	 * @returns contractee if found
	 * @throws ItemNotFoundException for not found
	 * @throws NotSupportedException for not supported classes
	 */
	public abstract Object getContracteeOfContracter(String contracter)
			throws ItemNotFoundException, NotSupportedException;

	/**
	 * Adds a contract to contract list
	 * @param contract that is going to be added
	 * @returns true if successfully added
	 */
	public boolean addContract(Contract contract) {
		return this.contracts.add(contract);
	}

	/**
	 * This function gets all contracts
	 * @returns contracts
	 */
	protected IContainer<Contract> getContracts() {
		return this.contracts;
	}

}
