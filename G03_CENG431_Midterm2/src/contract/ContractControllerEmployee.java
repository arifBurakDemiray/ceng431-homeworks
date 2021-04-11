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

	@Override
	// Takes manager name
	public IContainer<User> getContracterOfContractee(String mngName) throws ItemNotFoundException {
		IContainer<User> found = null;
		if (getContracts().getLength() < 1) {// if there is no contract
			throw new ItemNotFoundException("There is no contract.");
		}
		for (Contract contract : getContracts()) {// for each loop
			User tempManager = ((User) contract.getContractee()); // get manager
			String managerName = tempManager.getUserName();
			if (mngName.equals(managerName)) {// if manager name equals temp name
				found = castToUserContainer(contract.getContracter());// take it and break
				break;
			}
		}
		if (found == null) {// if there is no employees
			throw new ItemNotFoundException("The manager " + mngName + " has no employee.");
		} else {
			return found;
		}
	}

	@SuppressWarnings("unchecked")
	// This function casts an object to userContainer
	private IContainer<User> castToUserContainer(Object object) {
		IContainer<User> users = null;
		if (object instanceof IContainer<?>) {// If it is a user container cast and return it
			if (object.getClass().getAnnotatedSuperclass().toString().contains("User")) {
				users = (IContainer<User>) object;
			}
		}
		return users;
	}

	@Override
	public User getContracteeOfContracter(String contracter) throws NotSupportedException {
		throw new NotSupportedException("This operation is not supported.");
		// not supported for that contract
	}

	// adds an employee to manager's employees
	public void addEmployee(User manager, User createdUser) {
		try {
			IContainer<User> employeesOfManager = this.getContracterOfContractee(manager.getUserName());
			employeesOfManager.add(createdUser);// get manager and add it
			System.out.println("User " + createdUser.getUserName() + " is created."); // print info
		} catch (ItemNotFoundException e) {// if not found a contract create a new contract
			createNewContract(manager, createdUser);
		}

	}

	// this function creates a new contract for new employee
	private void createNewContract(User manager, User newEmployee) {
		IContainer<User> users = new UserContainer(); // new employee repo
		users.add(newEmployee); // add employee
		Contract newContract = new ContractManagerEmployee(manager, users);// create new contract
		getContracts().add(newContract);// add to contracts
		System.out.println("User " + newEmployee.getUserName() + " is created. And new contract created");
		// print info
	}

}
