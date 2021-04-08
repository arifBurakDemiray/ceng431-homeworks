package contract;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import storage.UserContainer;
import user.User;

public class ContractControllerEmployee extends ContractController {

	public ContractControllerEmployee(IContainer<Contract> contracts) {
		super(contracts);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IContainer<User> getContracterOfContractee(String mngName) throws ItemNotFoundException {
		IContainer<User> found = null;
		if(getContracts().getLength()<1)
		{
			throw new ItemNotFoundException("The manager " + mngName + " has no employee.");
		}
		for (Contract contract : getContracts()) {
			User tempManager = ((User) contract.getContractee());
			String managerName = tempManager.getUserName();
			if (mngName.equals(managerName)) {
				found = (IContainer<User>) contract.getContracter();
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("The manager " + mngName + " has no employee.");
		} else {
			return found;
		}
	}

	@Override
	public User getContracteeOfContracter(String contracter) throws NotSupportedException {
		throw new NotSupportedException("This operation is not supported.");
	}
	
	public void addEmployee(User manager, User createdUser) {
		try {
			this.getContracterOfContractee(manager.getUserName()).add(createdUser);
			System.out.println("User " + createdUser.getUserName() + " is created.");
		} catch (ItemNotFoundException e) {
			IContainer<User> users = new UserContainer();
			users.add(createdUser);
			Contract newContract = new ContractManagerEmployee(manager, users);
			getContracts().add(newContract);
			System.out.println("User " + createdUser.getUserName() + " is created. And new contract created");
		}

	}

}
