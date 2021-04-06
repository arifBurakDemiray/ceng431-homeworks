package storage;


import product.Assembly;
import product.Product;

public class StorageHelper {

	protected static Product recursiveGetBy(IContainer<Product> products, String value, Product returnedProduct, boolean flag) {
	
		for (Product product : products) {
			if (chooseString(flag,product).equals(value)) {
				returnedProduct = product;
				break;
			}
			else if(product instanceof Assembly) {
				returnedProduct = recursiveGetBy(((Assembly) product).getProducts(), value,returnedProduct,flag);
				if (returnedProduct != null && chooseString(flag,returnedProduct).equals(value)) {
					break;
				}
			}
		}
		return returnedProduct;
	}
	
	private static String chooseString(boolean flag,Product product){
		if(flag)
			return product.getId();
		return product.getTitle();
	}
	
}
