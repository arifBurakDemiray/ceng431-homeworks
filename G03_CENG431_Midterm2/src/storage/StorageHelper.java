package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import product.Assembly;
import product.Product;
import exception.ItemNotFoundException;

public class StorageHelper {

	protected StorageHelper() {
		
		
	}
	protected static void recursiveGetById(IContainer<Product> products,String id,Product returnedProduct) {
		for (Product product : products) {
			if (product.getId().equals(id)) {

				returnedProduct = product;
				break;
			}
			else if(product instanceof Assembly) {
				recursiveGetById(products, id,returnedProduct);
			}
		}
		if (returnedProduct == null) {
			throw new ItemNotFoundException("There is no product has id " + id);
		} 
		
		
		
	}
}
