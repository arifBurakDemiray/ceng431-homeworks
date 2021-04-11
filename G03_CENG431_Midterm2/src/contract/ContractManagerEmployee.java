package contract;

import storage.IContainer;
import user.User;

/**
 *This class represents contract between a manager and an employee
 */
public class ContractManagerEmployee extends Contract {

	public ContractManagerEmployee(User mngr, IContainer<User> emp) {
		super(mngr, emp);
	}

	public String toString() {//toString method modified to write in a json file
		User mngr = (User) this.getContractee();
		//this helper class is for help users to write in a json file
		String users = ContractHelper.toString(this.getContracter());
		String jsonValue = "\"" + mngr.getUserName() + "\":" + users;
		return jsonValue;
	}

}
