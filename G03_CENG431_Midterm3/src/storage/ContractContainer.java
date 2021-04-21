package storage;

import contract.Contract;
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
	 */
	@Override
	public Contract getByName(String name) throws NotSupportedException {
		throw new NotSupportedException("Contract container does not supports getByName() function");

	}

}
