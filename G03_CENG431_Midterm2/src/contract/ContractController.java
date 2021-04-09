package contract;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;

public abstract class ContractController {

	private IContainer<Contract> contracts;

	public ContractController(IContainer<Contract> contracts) {
		this.contracts = contracts;
	}

	public abstract Object getContracterOfContractee(String contractee) throws ItemNotFoundException;
	
	public abstract Object getContracteeOfContracter(String contracter) throws ItemNotFoundException, NotSupportedException;

	public boolean addContract(Contract contract) {
		return this.contracts.add(contract);
	}
	
	protected IContainer<Contract> getContracts(){
		return this.contracts;
	}
	
	
}
