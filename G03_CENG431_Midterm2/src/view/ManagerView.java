package view;

import org.json.JSONException;
import org.json.JSONObject;

import contract.ContractController;
import contract.ContractControllerEmployee;
import contract.ContractControllerProduct;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserException;
import factory.CreationResult;
import factory.Creator;
import fileio.FileController;
import product.Assembly;
import product.Product;
import storage.IContainer;
import user.User;

public class ManagerView extends UserView {
	private Product managerProduct;

	protected ManagerView(User user, FileController fileController, Creator creator,
			ContractController contractControllerProduct, ContractController contractControllerEmployee) {
		super(user, fileController, creator, contractControllerProduct, contractControllerEmployee);
	}

	@Override
	public void start() {
		try {
			managerProduct = (Product) this.contractControllerProduct
					.getContracterOfContractee(getUser().getUserName());
			menu();
		} catch (ItemNotFoundException e) {
			String managerName = getUser().getUserName();
			System.out.println(managerName + " has no product");
		}

	}

	public void createEmployee() {
		System.out.println("\n\tUser Creation");
		String userName = inputReceiver.getString("Employee username : ");
		String userPassword = inputReceiver.getString("Employee password : ");

		CreationResult cr = creator.createUser(userName, "Employee", userPassword);
		if (cr.object == null)
			System.out.println(cr.message);
		else {
			User createdUser = (User) cr.object;
			try {
				userController.createUser(createdUser, fileController);
				((ContractControllerEmployee) contractControllerEmployee).addEmployee(this.getUser(), createdUser);

			} catch (UnauthorizedUserException e) {
				System.out.println(e.getMessage());
			}

		}
	}

	public void createProduct() {
		System.out.println("\n\tProduct Creation");
		String productTitle = inputReceiver.getString("Product Title : ");
		String productType = inputReceiver.getString("Product Type : \"Assembly\" or \"Part\"");
		CreationResult cr = creator.createProduct(productType, productTitle, null, null);
		if (cr.object == null) {
			System.out.println(cr.message);
		} else {
			Product createdProduct = (Product) cr.object;
			((Assembly) managerProduct).addProduct(createdProduct);
			System.out.println("Product : " + productTitle + " ( " + productType + " ) is produced.");
		}

	}

	public void assignPartToEmployee() {
		System.out.println("\n\tAssign A Part To Employee");
		try {
			IContainer<User> employeesOfManager = ((ContractControllerEmployee) contractControllerEmployee)
					.getContracterOfContractee(this.getUser().getUserName());
			String emptyProducts = ViewHelper.findManagerEmptyProducts(managerProduct,
					(ContractControllerProduct) contractControllerProduct);
			String emptyEmployees = ViewHelper.findManagerEmptyEmployees(employeesOfManager,
					(ContractControllerProduct) contractControllerProduct);
			if (emptyProducts.equals("") || emptyEmployees.equals("")) {
				System.out.println("Employee or Part doesn't exist");
			} else {
				System.out.println(emptyProducts + "\n" + emptyEmployees);
				String partId = inputReceiver.getString("Part ID : ");
				String employeeName = inputReceiver.getString("Employee Name : ");
				Product part;
				User employee;
				part = fileController.products().getById(partId);
				employee = fileController.users().getByName(employeeName);
				userController.assignProduct(employee, part, fileController, contractControllerProduct);

			}

		} catch (ItemNotFoundException | NotSupportedException | UnauthorizedUserException e) {
			System.out.println(e.getMessage());
		}

	}

	public void printAll() throws JSONException {
		String productString = "{" + managerProduct.toString() + "}";
		JSONObject jsonProduct = new JSONObject(productString);
		System.out.println(jsonProduct.toString(4));

	}

	public void menu() {
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
				try {
					printAll();
				} catch (JSONException e) {
					System.out.println(e.getMessage());
				}
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
