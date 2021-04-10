package view;

import contract.ContractController;
import contract.ContractControllerEmployee;
import contract.ContractControllerProduct;
import exception.UnauthorizedUserException;
import factory.Creator;
import fileio.FileController;
import user.Admin;
import user.Employee;
import user.Manager;
import user.User;
import user.UserController;

public class UserView extends View{
	
	protected UserController userController;
	protected ContractController contractControllerProduct;
	protected ContractController contractControllerEmployee;
	private User user;
	public UserView(User user,FileController fileController,Creator creator) {
		super(fileController,creator);
		this.user = user;
		this.userController = new UserController(user);
		this.contractControllerProduct = new ContractControllerProduct(fileController.productContracts());
		this.contractControllerEmployee = new ContractControllerEmployee(fileController.employeeContracts());
	}
	
	protected UserView(User user,FileController fileController,Creator creator,ContractController contractControllerProduct){
		super(fileController,creator);
		this.user = user;
		this.userController = new UserController(user);
		this.contractControllerProduct = contractControllerProduct;
	}
	
	protected UserView(User user,FileController fileController,Creator creator,ContractController contractControllerProduct, ContractController contractControllerEmployee){
		super(fileController,creator);
		this.user = user;
		this.userController = new UserController(user);
		this.contractControllerProduct = contractControllerProduct;
		this.contractControllerEmployee = contractControllerEmployee;
	}
	
	protected User getUser() {
		return user;
	}
	
	protected void logout() {
		this.user = null;
		System.out.println("You successfully logged out.");
	}

	private void navigate() throws UnauthorizedUserException {
		if (this.user instanceof Admin) {
			(new AdminView(user,fileController,creator,contractControllerProduct)).start();
		} else if (this.user instanceof Manager) {
			(new ManagerView(user,fileController,creator,contractControllerProduct,contractControllerEmployee)).start();
		} else if (this.user instanceof Employee) {
			(new EmployeeView(user, fileController, creator, contractControllerProduct)).start();
		}
	}

	@Override
	public void start() throws UnauthorizedUserException {
		menu();
		navigate();
	}

	@Override
	protected void menu() throws UnauthorizedUserException{
		System.out.println("Welcome to the system "+user.getUserName());
	}
	
}
