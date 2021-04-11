package view;

import contract.ContractController;
import contract.ContractControllerEmployee;
import contract.ContractControllerProduct;
import exception.UnauthorizedUserException;
import factory.ICreatorService;
import fileio.FileController;
import user.Admin;
import user.Employee;
import user.Manager;
import user.User;
import user.UserController;

public class UserView extends View {

	protected UserController userController; // it is to invoke functions for user.
	protected ContractController contractControllerProduct; // it controls the contract between product and user
	protected ContractController contractControllerEmployee; // it controls the contract between employees and manager
	private User user;

	/**
	 * The constructor with given values for UserView
	 * 
	 * @param user           = loggedInUser
	 * @param fileController = holds containers ( file data )
	 * @param creator        = creator to create any type of instance of class
	 */
	public UserView(User user, FileController fileController, ICreatorService creator) {
		super(fileController, creator);
		this.user = user;
		this.userController = new UserController(user);
		this.contractControllerProduct = new ContractControllerProduct(fileController.productContracts());
		this.contractControllerEmployee = new ContractControllerEmployee(fileController.employeeContracts());
	}

	/**
	 * The constructor with given values for UserView
	 * 
	 * @param user
	 * @param fileController            = holds containers ( file data )
	 * @param creator                   = creator to create any type of instance of
	 *                                  class
	 * @param contractControllerProduct = it controls the contract between product
	 *                                  and user
	 */
	protected UserView(User user, FileController fileController, ICreatorService creator,
			ContractController contractControllerProduct) {
		super(fileController, creator);

		this.user = user;
		this.userController = new UserController(user);
		this.contractControllerProduct = contractControllerProduct;
	}

	/**
	 * The constructor with given values for UserView
	 * 
	 * @param user
	 * @param fileController             = holds containers ( file data )
	 * @param creator                    = creator to create any type of instance of
	 *                                   class
	 * @param contractControllerProduct  = it controls the contract between product
	 *                                   and user
	 * @param contractControllerEmployee = it controls the contract between
	 *                                   employees and manager
	 */
	protected UserView(User user, FileController fileController, ICreatorService creator,
			ContractController contractControllerProduct, ContractController contractControllerEmployee) {
		super(fileController, creator);
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

	/**
	 * The function navigates user according to user's type
	 * 
	 * @throws UnauthorizedUserException
	 */
	private void navigate() throws UnauthorizedUserException {
		if (this.user instanceof Admin) {
			(new AdminView(user, fileController, creator, contractControllerProduct)).start();
		} else if (this.user instanceof Manager) {
			(new ManagerView(user, fileController, creator, contractControllerProduct, contractControllerEmployee))
					.start();
		} else if (this.user instanceof Employee) {
			(new EmployeeView(user, fileController, creator, contractControllerProduct)).start();
		}
	}

	/**
	 * The function initialise the processes of the view
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	@Override
	public void start() throws UnauthorizedUserException {
		menu();
		navigate();
	}

	/**
	 * The function prints menu
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	@Override
	protected void menu() throws UnauthorizedUserException {
		System.out.println("Welcome to the system " + user.getUserName());
	}

}
