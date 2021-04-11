package fileio;

import product.Assembly;
import product.Product;
import storage.IContainer;

public class FileIOHelper {
	//This function updates main products states automatically
	protected static void updateProductsStates(IContainer<Product> products) {

		for (Product product : products) {
			if (product instanceof Assembly)
				product.updateState();
		}
	}
}
