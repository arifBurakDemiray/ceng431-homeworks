package contract;

import storage.IContainer;
import user.User;

public class ContractManagerEmployee extends Contract {

	
	public ContractManagerEmployee(User mngr, IContainer<User> emp) {
		super(mngr,emp);
	}

	
	public String toString() {
		User mngr = (User)this.getContractee();
		String users = ContractHelper.toString(this.getContracter());
		String jsonValue = "\""+mngr.getUserName()+"\":"+users;
		return jsonValue;
	}
	
}
