package view;

import java.util.Iterator;

import contract.ContractController;
import contract.ContractControllerProduct;
import exception.ItemNotFoundException;
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
					((ContractControllerProduct) contractController).getContracterOfContractee(user.getUserName());
					
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
				((ContractControllerProduct) contractController).getContracteeOfContracter(product.getId());
			} catch (ItemNotFoundException e) {
				products += (product.getId() + ":" + product.getTitle() + "\n");
			}
		}
		return products;
	}

	public static String findManagerEmptyProducts(Product managerProduct, ContractController contractController) {
		String products = "";
		products = recursiveEmptyProducts(managerProduct, contractController, products);
		return products;
	}

	private static String recursiveEmptyProducts(Product mainProduct, ContractController contractController,
			String products) {

		IContainer<Product> temp = ((Assembly) mainProduct).getProducts();
		Iterator<Product> it = temp.iterator();
		Product product = null;
		while (it.hasNext()) {
			product = it.next();
			if (product instanceof Assembly)
			{
				products=recursiveEmptyProducts(product, contractController, products);
			}
			else {
				try {
					((ContractControllerProduct) contractController).getContracteeOfContracter(product.getId());
				} catch (ItemNotFoundException e) {
					products += (product.getTitle() + " : " + product.getId() + "\n");
				}
			}
		}
		
		return products;

	}

	public static String findManagerEmptyEmployees(IContainer<User> employeeContainer,
			ContractController contractController) {
		String employees = "";
		if(employeeContainer != null)
		{
			for (User employee : employeeContainer) {
				try {
					
					((ContractControllerProduct) contractController).getContracterOfContractee(employee.getUserName());
				} catch (ItemNotFoundException e) {
					employees += (employee.getUserName() + "\n");
				}
			}
		}
		
		return employees;
	}

}
