package view;

import contract.ContractController;
import exception.ItemNotFoundException;
import exception.UnauthorizedUserException;
import factory.ICreatorService;
import fileio.FileController;
import product.Product;
import user.User;

public class EmployeeView extends UserView {

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

	protected EmployeeView(User user, FileController fileController, ICreatorService creator,
			ContractController contractControllerProduct) {
		super(user, fileController, creator, contractControllerProduct);
	}

	/**
	 * The function initialise the processes of the employee view
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	@Override
	public void start() throws UnauthorizedUserException {
		menu();

	}

	/**
	 * The function updates the state of assigned part of employee ( if exists )
	 * 
	 * @throws UnauthorizedUserException
	 */
	private void changeProductStatus() throws UnauthorizedUserException {
		try {
			// try to find assigned product exists or not.
			Product prd = (Product) contractControllerProduct.getContracterOfContractee(this.getUser().getUserName());
			if (!prd.getProductState().equals("Completed")) {
				System.out.println("State is updated");
				// Update state by invoking userController.updateProduct()
				// to detect logged in user is authorised for process and
				// to update the product's state
				userController.updateProduct(prd);
			} else {
				System.out.println("This product is already completed.");
			}
		} catch (ItemNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Print assigned part of employee
	 */
	private void printPart() {
		try {
			Product prd = (Product) contractControllerProduct.getContracterOfContractee(this.getUser().getUserName());
			System.out.println(
					"\nTitle: " + prd.getTitle() + "\nId: " + prd.getId() + "\nState: " + prd.getProductState() + "\n");
		} catch (ItemNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * The function prints menu and get a menu choice from user
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	protected void menu() throws UnauthorizedUserException {
		String menuString = "1: Change Product Status\n2: Print Part\n3: Print Menu\n4: Logout";
		System.out.println("\n\tEMPLOYEE MENU\n\n" + menuString);
		String menuChoice = "";
		while (!menuChoice.equals("4")) {
			menuChoice = inputReceiver.getString("\nChoice : ");
			switch (menuChoice) {
			case "1": {
				changeProductStatus();
				break;
			}
			case "2": {
				printPart();
				break;
			}
			case "3": {
				System.out.println(menuString);
				break;
			}
			case "4":
				super.logout();
				break;
			default:
				System.out.println("Write a valid number.");
			}
		}
	}

}
