package storage;

import contract.Contract;
import exception.NotSupportedException;

public class ContractContainer extends Container<Contract>{

	@Override
	public Contract getById(String id) throws NotSupportedException {
		throw new NotSupportedException("Contract container does not supports getById() function");
	}

	@Override
	public Contract getByName(String name) throws NotSupportedException {
		throw new NotSupportedException("Contract container does not supports getByName() function");
		
	}

}
