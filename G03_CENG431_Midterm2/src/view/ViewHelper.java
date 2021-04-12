package view;

import java.util.Iterator;

import contract.ContractController;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.FileController;
import product.Assembly;
import product.Product;
import storage.IContainer;
import user.Manager;
import user.User;

public class ViewHelper {

	/**
	 * Invoked from admin view The function is a helper function which finds empty
	 * main products and empty managers by invoking findEmptyProducts() and
	 * findEmptyManagers()
	 * 
	 * @param fileController     = fileController object which holds containers (
	 *                           file data )
	 * @param contractController = given contractController object which holds
	 *                           contracts
	 * @return String print list or warning message.
	 */
	protected static String assingProductList(FileController fileController, ContractController contractController) {
		String productList = findEmptyProducts(fileController, contractController);
		System.out.println(productList);
		String managerList = findEmptyManagers(fileController, contractController);
		System.out.println(managerList);
		String msg = "";
		if (productList.equals("")) {
			msg += "There is no empty product to assign.\n";
		}
		if (managerList.equals("")) {
			msg += "There is no empty manager to assign a product.\n";
		}
		return msg;
	}

	/**
	 * Invoked by assingProductList() function The function travels to user
	 * container which is gotten from fileController to detect managers which have
	 * no assigned products
	 * 
	 * @param fileController
	 * @param contractController
	 * @return String of empty managers' name
	 */
	private static String findEmptyManagers(FileController fileController, ContractController contractController) {
		String managers = "";

		// If user is a Manager, tries to find the product contract of user to detect
		// that user has an assigned product or not.
		// If there is no assigned product, holds the user name
		for (User user : fileController.users()) {
			if (user instanceof Manager) {
				try {
					contractController.getContracterOfContractee(user.getUserName());
				} catch (ItemNotFoundException e) {
					managers += (user.getUserName() + "\n");
				}
			}
		}
		return managers;
	}

	/**
	 * Invoked by assingProductList() function The function travels to product
	 * container which is gotten from fileController to detect products which have
	 * no assigned user
	 * 
	 * @param fileController
	 * @param contractController
	 * @return String of empty products' info
	 */
	private static String findEmptyProducts(FileController fileController, ContractController contractController) {
		String products = "";
		for (Product product : fileController.products()) {

			// Try to find the product contract to detect that product has an assigned user
			// or not.
			// If there is no assigned user, hold the product info
			try {
				contractController.getContracteeOfContracter(product.getId());
			} catch (ItemNotFoundException e) {
				products += (product.getId() + ":" + product.getTitle() + "\n");
			} catch (NotSupportedException e) {
			}
		}
		return products;
	}

	/**
	 * Invoked from ManagerView The function is a helper function which finds parts
	 * ,which have no assigned user, of manager product by invoking recursive
	 * function
	 * 
	 * @param managerProduct     = main product of manager
	 * @param contractController
	 * @return String of empty parts
	 */
	public static String findManagerProductsWithoutEmployee(Product managerProduct,
			ContractController contractController) {
		String products = "";
		products = recursiveProductsWithoutEmployee(managerProduct, contractController, products);
		return products;
	}

	/**
	 * Invoked by findManagerProductsWithoutEmployee() The function iterates in the
	 * inner parts of prodcutTaken to detect the part which has no assigned user.
	 * 
	 * @param productTaken
	 * @param contractController
	 * @param products           String list of empty parts
	 * @return String of products' info
	 */
	private static String recursiveProductsWithoutEmployee(Product productTaken, ContractController contractController,
			String products) {

		IContainer<Product> temp = ((Assembly) productTaken).getProducts();
		Iterator<Product> it = temp.iterator();
		Product product = null;

		// Iterate in productTaken ( assembly of a main product)
		while (it.hasNext()) {
			product = it.next();

			// If gotten product is an assembly, do recursive to reach inner part or
			// assembly of product item.
			if (product instanceof Assembly) {
				products = recursiveProductsWithoutEmployee(product, contractController, products);
			} else {
				// If product is a part, control that it has assigned user or not
				try {
					contractController.getContracteeOfContracter(product.getId());
				} catch (ItemNotFoundException e) {
					// if there is no assigned user for product, hold the product info.
					products += (product.getTitle() + " : " + product.getId() + "\n");
				} catch (NotSupportedException e) {
				}
			}
		}

		return products;

	}

	/**
	 * Invoked from ManagerView The function is a helper function which finds
	 * employees ,which have no assigned part, of manager product
	 * 
	 * @param employeeContainer  which is gotten from contractControllerEmployee in
	 *                           ManagerView, holds manager's employee list
	 * @param contractController
	 * @return String of empty employees
	 */
	public static String findManagerEmployeesWithoutProduct(IContainer<User> employeeContainer,
			ContractController contractController) {
		String employees = "";
		if (employeeContainer != null) {

			// Travel to employeeContainer
			for (User employee : employeeContainer) {
				try {
					// Try to detect employee has an assigned product or not
					contractController.getContracterOfContractee(employee.getUserName());
				} catch (ItemNotFoundException e) {
					// If employee has no assigned product, hold employee name
					employees += (employee.getUserName() + "\n");
				}
			}
		}
		return employees;
	}

	/**
	 * Invoked from ManagerView createProduct() The function returns the assembly
	 * list of main product of manager using recursive
	 * 
	 * @param managerProduct = main product of manager
	 * @return String of assemblies
	 */
	public static String findManagerAssemblies(Product managerProduct) {
		String ids = "";
		ids = recursiveManagerAssemblies(managerProduct, ids, 0);
		return ids;
	}

	/**
	 * The function do recursive to reach inner assemblies of the productTaken
	 * (assembly)
	 * 
	 * @param productTaken = taken product
	 * @param ids          = id list of assemblies
	 * @param count        = holds the count number for count*(" ") not to losing
	 *                     the tree structure
	 * @return ids String of id
	 */
	private static String recursiveManagerAssemblies(Product productTaken, String ids, int count) {

		if (productTaken instanceof Assembly) {
			ids += productTaken.getId() + ","; // hold assembly id
			// print assembly without losing the tree structure
			System.out.println(("  ").repeat(count) + productTaken.getId() + "-" + productTaken.getTitle());
			count += 1;

			// get the product of productTaken (assembly) and do recursive in its products
			IContainer<Product> temp = ((Assembly) productTaken).getProducts();
			for (Product product : temp) {

				ids = recursiveManagerAssemblies(product, ids, count);
			}
		}
		return ids;
	}
	
	/**
	 * The function is a helper function which print users and their assigned
	 * products
	 * 
	 * @param userContainer             which is gotten from fileController in
	 *                                  ManagerView&AdminView, holds users list
	 * @param contractControllerProduct to detect user's product
	 * @return String of Strings of user and product
	 */
	public static String findUsers(IContainer<User> userContainer, ContractController contractControllerProduct) {
		String users = "";
		if (userContainer != null) {

			// Travel to userContainer
			for (User user : userContainer) {
				
				String userName = user.getUserName();
				if(!userName.equals("SYSADMIN"))
				{
					try {
						// Try to detect user's assigned product if exists
						contractControllerProduct.getContracterOfContractee(userName);
					} catch (ItemNotFoundException e) {				
						users += (userName + ":");
						// hold that message if user has no product
						users += (" No product found for " + userName + "\n");
					}
				}
				
			}
		}
		return users;
	}


	/**
	 * Invoked from AdminView and ManagerView The function is a helper function
	 * which print users and their assigned products which is in the taken product
	 * (main product)
	 * 
	 * @param product                   = given product
	 * @param contractControllerProduct to detect product's assigned user
	 */
	public static void findProductsAndUsers(Product product, ContractController contractControllerProduct) {
		System.out.println("\n");
		recursiveProductsAndUsers(product, 0, contractControllerProduct);
	}

	/**
	 * Invoked by findProductsAndUsers() The function do recursive to reach inner
	 * products of the productTaken (assembly)
	 * 
	 * @param productTaken
	 * @param count                     = holds the count number for count*(" ") not
	 *                                  to losing the tree structure
	 * @param contractControllerProduct
	 */
	private static void recursiveProductsAndUsers(Product productTaken, int count,
			ContractController contractControllerProduct) {

		// try to get the user of product if exists assigned user
		User user = getUserOfProduct(contractControllerProduct, productTaken);
		// Prepare the print message
		String message = configureMessage(productTaken, user, count);
		System.out.println(message);

		// If taken product is assembly, do recursive to reach inner products of that
		// assembly.
		if (productTaken instanceof Assembly) {
			count += 1;
			IContainer<Product> temp = ((Assembly) productTaken).getProducts();
			for (Product product : temp) {
				recursiveProductsAndUsers(product, count, contractControllerProduct);
			}
		}
	}

	/**
	 * Invoked by recursiveProductsAndUsers() The function tries to find assigned
	 * user of given product
	 * 
	 * @param contractController
	 * @param product            = given product
	 * @return User assigned user or null
	 */
	private static User getUserOfProduct(ContractController contractController, Product product) {
		User result = null;
		String productId = product.getId();
		try {
			User user = (User) contractController.getContracteeOfContracter(productId);
			result = user;
		} catch (ItemNotFoundException | NotSupportedException e) {
		}
		return result;
	}

	/**
	 * Invoked by recursiveProductsAndUsers() The function prints the product and
	 * user info without using tree structure
	 * 
	 * @param product
	 * @param user
	 * @param blankInt holds the count number for blankInt*(" ") not to losing the
	 *                 tree structure
	 * @return
	 */
	private static String configureMessage(Product product, User user, int blankInt) {
		String message = "";
		String lineVariable = ""; 
		String blank = ("  ").repeat(blankInt);
		if (product != null) {

			message = blank + "Product Title : " + product.getTitle() + "\n" + blank + "Product Id : " + product.getId()
					+ "\n" + blank + "Product State : " + product.getProductState() + "\n" + blank + "Product Type : "
					+ product.getClass().getSimpleName() + "\n" + blank;

		}
		if(user != null)
		{
			lineVariable = "Assigned User : " + user.getUserName() + " - " + user.getClass().getSimpleName();
		}
	
		String line = "-";
		message += lineVariable;
		message += "\n" + blank + line.repeat(lineVariable.length());
		return message;
	}

}
