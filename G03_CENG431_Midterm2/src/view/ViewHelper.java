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

	private static String findEmptyManagers(FileController fileController, ContractController contractController) {
		String managers = "";
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

	private static String findEmptyProducts(FileController fileController, ContractController contractController) {
		String products = "";
		for (Product product : fileController.products()) {

			try {
				contractController.getContracteeOfContracter(product.getId());
			} catch (ItemNotFoundException e) {
				products += (product.getId() + ":" + product.getTitle() + "\n");
			} catch (NotSupportedException e) {
			}
		}
		return products;
	}

	public static String findManagerProductsWithoutEmployee(Product managerProduct,
			ContractController contractController) {
		String products = "";
		products = recursiveProductsWithoutEmployee(managerProduct, contractController, products);
		return products;
	}

	private static String recursiveProductsWithoutEmployee(Product productTaken, ContractController contractController,
			String products) {

		IContainer<Product> temp = ((Assembly) productTaken).getProducts();
		Iterator<Product> it = temp.iterator();
		Product product = null;
		while (it.hasNext()) {
			product = it.next();
			if (product instanceof Assembly) {
				products = recursiveProductsWithoutEmployee(product, contractController, products);
			} else {
				try {
					contractController.getContracteeOfContracter(product.getId());
				} catch (ItemNotFoundException e) {
					products += (product.getTitle() + " : " + product.getId() + "\n");
				} catch (NotSupportedException e) {
				}
			}
		}

		return products;

	}

	public static String findManagerEmployeesWithoutProduct(IContainer<User> employeeContainer,
			ContractController contractController) {
		String employees = "";
		if (employeeContainer != null) {
			for (User employee : employeeContainer) {
				try {
					contractController.getContracterOfContractee(employee.getUserName());
				} catch (ItemNotFoundException e) {
					employees += (employee.getUserName() + "\n");
				}
			}
		}
		return employees;
	}

	public static String findUsers(IContainer<User> userContainer, ContractController contractControllerProduct) {
		String users = "";
		if (userContainer != null) {
			for (User user : userContainer) {
				String userName = user.getUserName();
				users += (userName + ":");
				try {
					Product prd = (Product) contractControllerProduct.getContracterOfContractee(userName);
					users += prd.getTitle();
				} catch (ItemNotFoundException e) {
					users += (" No product found for" + userName + "\n");
				}
			}
		}
		return users;
	}

	public static String findManagerAssemblies(Product managerProduct) {
		String ids = "";
		ids = recursiveManagerAssemblies(managerProduct, ids, 0);
		return ids;
	}

	public static void findProductsAndUsers(Product product, ContractController contractControllerProduct) {
		System.out.println("\n");
		recursiveProductsAndUsers(product, 0, contractControllerProduct);
	}

	private static void recursiveProductsAndUsers(Product productTaken, int count,
			ContractController contractControllerProduct) {

		User user = getUserOfProduct(contractControllerProduct, productTaken);
		String  message = configureMessage(productTaken, user, count);
		System.out.println(message);
		if (productTaken instanceof Assembly) {
			count += 1;
			IContainer<Product> temp = ((Assembly) productTaken).getProducts();
			for (Product product : temp) {

				recursiveProductsAndUsers(product, count, contractControllerProduct);
			}
		}
	}

	private static String configureMessage(Product product, User user, int blankInt) {
		String blank = ("  ").repeat(blankInt);
		String message = blank + "Product Title : " + product.getTitle() + "\n" + blank + "Product Id : "
				+ product.getId() + "\n" + blank + "Product State : " + product.getProductState() + "\n" + blank+
				"Product Type : "+product.getClass().getSimpleName()+"\n" +blank;
		String lineVariable = "Assigned User : " + user.getUserName() + " - " + user.getClass().getSimpleName();
		String line = "-";
		message += lineVariable;
		message += "\n" + blank + line.repeat(lineVariable.length());
		return message;
	}

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

	private static String recursiveManagerAssemblies(Product productTaken, String ids, int count) {

		if (productTaken instanceof Assembly) {
			ids += productTaken.getId() + ",";
			System.out.println(("  ").repeat(count) + productTaken.getId() + "-" + productTaken.getTitle());
			count += 1;
			IContainer<Product> temp = ((Assembly) productTaken).getProducts();
			for (Product product : temp) {

				ids = recursiveManagerAssemblies(product, ids, count);
			}
		}
		return ids;
	}

}
