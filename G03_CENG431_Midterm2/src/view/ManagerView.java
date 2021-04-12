package view;

import contract.ContractController;
import contract.ContractControllerEmployee;
import contract.ContractControllerProduct;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserException;
import factory.CreationResult;
import factory.ICreatorService;
import fileio.FileController;
import product.Product;
import storage.IContainer;
import user.User;

public class ManagerView extends UserView {
	private Product managerProduct; // it is the manager's assigned product if exists

	/**
	 * The constructor creates a View invoking superclass' constructor.
	 * 
	 * @param user                       = loggedInUser
	 * @param fileController             = holds containers ( file data )
	 * @param creator                    = creator to create any type of instance of
	 *                                   class
	 * @param contractControllerProduct  = it controls the contract between product
	 *                                   and user
	 * @param contractControllerEmployee = it controls the contract between
	 *                                   employees and manager
	 */
	protected ManagerView(User user, FileController fileController, ICreatorService creator,
			ContractController contractControllerProduct, ContractController contractControllerEmployee) {
		super(user, fileController, creator, contractControllerProduct, contractControllerEmployee);
	}

	/**
	 * The function initialise the processes of the manager view
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	@Override
	public void start() throws UnauthorizedUserException {
		try {
			// try to get manager's assigned product
			managerProduct = (Product) this.contractControllerProduct
					.getContracterOfContractee(getUser().getUserName());
			menu();
		} catch (ItemNotFoundException e) {
			String managerName = getUser().getUserName();
			System.out.println(managerName + " has no product");
		}

	}

	/**
	 * The function is for the create an employee for logged in manager
	 * 
	 * @throws UnauthorizedUserException
	 */
	public void createEmployee() throws UnauthorizedUserException {
		System.out.println("\n\tUser Creation");
		// Get employee name and password
		String userName = inputReceiver.getString("Employee username : ");
		String userPassword = inputReceiver.getString("Employee password : ");

		// Try to create a Employee using creator
		// If creation is not successful, print error message
		CreationResult cr = creator.createUser(userName, "Employee", userPassword);
		if (cr.object == null)
			System.out.println(cr.message);
		else {
			// If creation is successful invoke userController.createUser() to detect logged
			// in user is authorised and to add the created user to fileController
			User createdUser = (User) cr.object;
			userController.createUser(createdUser, fileController);
			// If all processes are done, add the employee to manager's employee container
			// which is hold by contractor.
			((ContractControllerEmployee) contractControllerEmployee).addEmployee(this.getUser(), createdUser);
		}
	}

	/**
	 * The function is for the create a product for logged in manager
	 */
	public void createProduct() {
		System.out.println("\n\tProduct Creation");
		// Get the existing assemblies of the manager product to select and to insert it
		// the new created product
		String ids = ViewHelper.findManagerAssemblies(managerProduct);
		String productId = inputReceiver.getString("Product ID which you wanted to insert new product  : ");
		// If selected assembly is in the manager's product, continue to process
		boolean isAssemblyPartOfProduct = ids.contains(productId);
		if (isAssemblyPartOfProduct) {
			// Get new product info and invoke userController.createProductForManager() to
			// add the created product to selected assembly
			String productTitle = inputReceiver.getString("Product Title : ");
			String productType = inputReceiver.getString("Product Type : \"Assembly\" or \"Part\": ");
			userController.createProductForManager(fileController, creator, productId, productType, productTitle);
		} else
			System.out.println("There is no assembly that has id " + productId);
	}

	/**
	 * * The function is for the assign a part of the manager's product to an
	 * employee for logged in manager
	 * 
	 * @throws UnauthorizedUserException
	 */
	public void assignPartToEmployee() throws UnauthorizedUserException {
		System.out.println("\n\tAssign A Part To Employee");
		try {
			// Try to get the employees of manager using ContractControllerEmployee
			IContainer<User> employeesOfManager = ((ContractControllerEmployee) contractControllerEmployee)
					.getContracterOfContractee(this.getUser().getUserName());

			// Get data of each empty part
			String emptyProducts = ViewHelper.findManagerProductsWithoutEmployee(managerProduct,
					(ContractControllerProduct) contractControllerProduct);
			// Get data of each empty employees
			String emptyEmployees = ViewHelper.findManagerEmployeesWithoutProduct(employeesOfManager,
					(ContractControllerProduct) contractControllerProduct);
			if (emptyProducts.equals("") || emptyEmployees.equals("")) {
				System.out.println("There is not enough employee or part to assign.");
			} else {
				System.out.println(emptyProducts + "\n" + emptyEmployees);

				// Get a product id and employee name from manager
				String partId = inputReceiver.getString("Part ID : ");
				String employeeName = inputReceiver.getString("Employee Name : ");

				// Try to find selected part and employee
				Product part = fileController.products().getById(partId);
				User employee = fileController.users().getByName(employeeName);

				// Assign the part to employee by invoking userController.assignProduct() to
				// detect logged in user is authorised
				// and to create a new contractUserProduct between part and employee
				userController.assignProduct(employee, part, fileController, contractControllerProduct);
			}

		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Print product tree and assigned users of parts
	 */
	public void printAll() {
		fileController.updateStatesOfProduct();
		System.out.println("\n\tPRODUCTS and EMPLOYEES\n");
		ViewHelper.findProductsAndUsers(managerProduct, contractControllerProduct);
		System.out.println("\n");
		try {
			IContainer<User> employees =((ContractControllerEmployee)contractControllerEmployee).getContracterOfContractee(this.getUser().getUserName());
			String userPrint = ViewHelper.findUsers(employees, contractControllerProduct);
			System.out.println(userPrint);
		} catch (ItemNotFoundException e) {
			System.out.println("There is no employee");
		}
		
	}

	/**
	 * The function prints menu and get a menu choice from user
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	public void menu() throws UnauthorizedUserException {
		String menuString = "1: Create Employee\n2: Create Product\n3: Assign a Part to an Employee\n"
				+ "4: Print Products and Users\n5: Print Menu\n6: Logout";
		System.out.println("\n\tMANAGER MENU\n\n" + menuString);
		String menuChoice = "";
		while (!menuChoice.equals("6")) {
			menuChoice = inputReceiver.getString("\nChoice : ");
			switch (menuChoice) {
			case "1": {
				createEmployee();
				break;
			}
			case "2": {
				createProduct();
				break;
			}
			case "3": {
				assignPartToEmployee();
				break;
			}
			case "4": {
				printAll();
				break;
			}
			case "5": {
				System.out.println(menuString);
				break;
			}
			case "6":
				super.logout();
				break;
			default:
				System.out.println("Write a valid number.");
			}
		}
	}

}
