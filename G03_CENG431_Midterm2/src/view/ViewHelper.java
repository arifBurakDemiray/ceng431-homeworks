package view;

import contract.ContractController;
import exception.ItemNotFoundException;
import fileio.FileController;
import product.Product;
import user.Manager;
import user.User;

public class ViewHelper {
	
	protected static String assingProductList(FileController fileController, ContractController contractController) {
		String productList = findEmptyProducts(fileController,contractController);
		System.out.println(productList);
		String managerList = findEmptyManagers(fileController,contractController);
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
				boolean isManagerEmpty = true;
				try {
					isManagerEmpty = (contractController.getUserProduct(user.getUserName()) == null);
				} catch (ItemNotFoundException e) {

				}
				if (isManagerEmpty) {
					managers += (user.getUserName() + "\n");
				}
			}
		}
		return managers;
	}

	private static String findEmptyProducts(FileController fileController, ContractController contractController) {
		String products = "";
		for (Product product : fileController.products()) {
			boolean isProductEmpty = true;
			try {
				isProductEmpty = (contractController.getProductOfUser(product.getId()) == null);
			} catch (ItemNotFoundException e) {
			}
			if (isProductEmpty) {
				products += (product.getId() + ":" + product.getTitle() + "\n");
			}
		}
		return products;
	}

}
