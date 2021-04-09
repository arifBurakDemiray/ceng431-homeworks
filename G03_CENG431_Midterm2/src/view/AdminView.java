package view;

import org.json.JSONException;
import org.json.JSONObject;

import contract.ContractController;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserException;
import factory.CreationResult;
import factory.Creator;
import fileio.FileController;
import product.Product;
import user.User;

public class AdminView extends UserView {

	protected AdminView(User user, FileController fileController, Creator creator,
			ContractController contractControllerProduct) {
		super(user, fileController, creator, contractControllerProduct);
	}

	@Override
	public void start() {
		menu();
	}

	private void createManager() {
		System.out.println("\n\tUser Creation");
		String userName = inputReceiver.getString("Username : ");
		String userPassword = inputReceiver.getString("User Password : ");

		CreationResult cr = creator.createUser(userName, "Manager", userPassword);
		if (cr.object == null)
			System.out.println(cr.message);
		else {
			User createdUser = (User) cr.object;
			try {
				userController.createUser(createdUser, fileController);
				System.out.println("User " + userName + " is created succesfully");
			} catch (UnauthorizedUserException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void createProduct() {
		System.out.println("\n\tProduct Creation");
		String productTitle = inputReceiver.getString("Product Title : ");
		CreationResult cr = creator.createProduct("Assembly", productTitle, null, null);
		if (cr.object == null) {
			System.out.println(cr.message);
		} else {
			Product createdProduct = (Product) cr.object;
			try {
				userController.createProduct(createdProduct, fileController);
				System.out.println("Product : " + productTitle + " is produced.");
			} catch (UnauthorizedUserException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private void assignProductToManager() {
		System.out.println("\n\tAssign A Product To Manager");
		String message = ViewHelper.assingProductList(fileController,contractControllerProduct);
		if (!message.equals("")) {
			System.out.println(message);
			return;
		}
		String productID = inputReceiver.getString("Product ID : ");
		String managerName = inputReceiver.getString("Manager Name : ");
		Product product;
		User manager;
		try {
			product = fileController.getByProductId(productID);
			manager = fileController.getByUserName(managerName);
			userController.assignProduct(manager, product, fileController, contractControllerProduct);
		} catch (ItemNotFoundException | NotSupportedException | UnauthorizedUserException e) {
			System.out.println(e.getMessage());
		}
	}

	private void printAll() throws JSONException {

		String productList = "{" + fileController.products().toString() + "}";
		JSONObject jsonProduct = new JSONObject(productList);
		String userList = "{" + fileController.users().toString() + "}";
		JSONObject jsonUser = new JSONObject(userList);
		String jsonStringProduct = jsonProduct.toString(4);
		String jsonStringUser = jsonUser.toString(4);
		String formattedProducts = StringHelper.printProductTree(jsonStringProduct);
		System.out.println(formattedProducts);
		System.out.println(jsonStringUser);

	}
	protected void menu() {
		String menuString = "1: Create Manager \n2: Create Product\n3: Assign a Product to a Manager\n"
				+ "4: Print Products and Users\n5: Print Menu\n6: Logout";
		System.out.println("\n\tADMIN MENU\n\n"+menuString);
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
				try {
					printAll();
				} catch (JSONException e) {
					System.out.println(e.getMessage());}
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
