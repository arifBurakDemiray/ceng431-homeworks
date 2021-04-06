package storage;

import product.Assembly;
import product.Product;

public class StorageHelper {

	protected StorageHelper() {

	}

	protected static Product recursiveGetById(IContainer<Product> products, String id, Product returnedProduct) {
	
		for (Product product : products) {
			if (product.getId().equals(id)) {

				returnedProduct = product;
				break;
			}
			else if(product instanceof Assembly) {
				returnedProduct = recursiveGetById(((Assembly) product).getProducts(), id,returnedProduct);
				if (returnedProduct != null && returnedProduct.getId().equals(id)) {
					break;
				}
			}
		}

		return returnedProduct;
	}
}
