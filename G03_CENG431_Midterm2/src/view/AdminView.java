package view;

import contract.ContractController;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserException;
import factory.CreationResult;
import factory.ICreatorService;
import fileio.FileController;
import product.Product;
import user.User;

public class AdminView extends UserView {

	/**
	 * The constructor creates a View invoking superclass' constructor.
	 * 
	 * @param user                      = loggedInUser
	 * @param fileController            = holds containers ( file data )
	 * @param creator                   = creator to create any type of instance of
	 *                                  class
	 * @param contractControllerProduct = it controls the contract between product
	 *                                  and user
	 */

	protected AdminView(User user, FileController fileController, ICreatorService creator,

			ContractController contractControllerProduct) {
		super(user, fileController, creator, contractControllerProduct);
	}

	/**
	 * The function initialise the processes of the admin view
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	@Override
	public void start() throws UnauthorizedUserException {
		menu();
	}

	/**
	 * The function is for the create a manager for logged in admin
	 * 
	 * @throws UnauthorizedUserException
	 */
	private void createManager() throws UnauthorizedUserException {
		System.out.println("\n\tUser Creation");
		// Get manager name and password
		String userName = inputReceiver.getString("Username : ");
		String userPassword = inputReceiver.getString("User Password : ");

		// Try to create a Employee using creator
		// If creation is not successful, print error message
		CreationResult cr = creator.createUser(userName, "Manager", userPassword);
		if (cr.object == null)
			System.out.println(cr.message);
		else {
			// If creation is successful invoke userController.createUser() to detect logged
			// in user is authorised and to add the created user to fileController
			User createdUser = (User) cr.object;
			userController.createUser(createdUser, fileController);
			System.out.println("User " + userName + " is created succesfully");
		}
	}

	/**
	 * The function is for the create a product for logged in manager
	 */
	private void createProduct() throws UnauthorizedUserException {
		System.out.println("\n\tProduct Creation");
		String productTitle = inputReceiver.getString("Product Title : ");

		// Try to create a product using creator
		// If creation is not successful, print error message
		CreationResult cr = creator.createProduct("Assembly", productTitle, null, null);
		if (cr.object == null) {
			System.out.println(cr.message);
		} else {

			// If creation is successful invoke userController.createProduct() to detect
			// logged
			// in user is authorised and to add the created user to fileController

			Product createdProduct = (Product) cr.object;
			userController.createProduct(createdProduct, fileController);
			System.out.println("Product : " + productTitle + " is produced.");
		}

	}

	/**
	 * * The function is for the assign a product to a manager for logged in admin
	 * 
	 * @throws UnauthorizedUserException
	 */
	private void assignProductToManager() throws UnauthorizedUserException {
		System.out.println("\n\tAssign A Product To Manager");
		// Get data of each empty product and empty manager
		String message = ViewHelper.assingProductList(fileController, contractControllerProduct);
		if (!message.equals("")) {
			System.out.println(message);
			return;
		}
		String productID = inputReceiver.getString("Product ID : ");
		String managerName = inputReceiver.getString("Manager Name : ");
		try {

			// Try to find selected product and manager
			Product product = fileController.getByProductId(productID);
			User manager = fileController.getByUserName(managerName);

			// Assign the product to manager by invoking userController.assignProduct() to
			// detect logged in user is authorised
			// and to create a new contractUserProduct between product and employee
			userController.assignProduct(manager, product, fileController, contractControllerProduct);
		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Print products' tree and assigned users of products
	 */
	private void printAll() {
		fileController.updateStatesOfProduct();
		for (Product product : fileController.products()) {
			ViewHelper.findProductsAndUsers(product, contractControllerProduct);
		}
		
		String userPrint = ViewHelper.findUsers(fileController.users(), contractControllerProduct);
		System.out.println(userPrint);
	}

	/**
	 * The function prints menu and get a menu choice from user
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	protected void menu() throws UnauthorizedUserException {
		String menuString = "1: Create Manager \n2: Create Product\n3: Assign a Product to a Manager\n"
				+ "4: Print Products and Users\n5: Print Menu\n6: Logout";
		System.out.println("\n\tADMIN MENU\n\n" + menuString);
		String menuChoice = "";
		while (!menuChoice.equals("6")) {
			menuChoice = inputReceiver.getString("\nChoice : ");
			switch (menuChoice) {
			case "1":
				createManager();
				break;
			case "2":
				createProduct();
				break;
			case "3":
				assignProductToManager();
				break;
			case "4":
				printAll();
				break;
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
