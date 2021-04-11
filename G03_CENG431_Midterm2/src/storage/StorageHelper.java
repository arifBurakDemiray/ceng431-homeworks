package storage;


import product.Assembly;
import product.Product;

public class StorageHelper {
	
	/**
	 * The function is a helper function which tries to find product of given product id or title using recursive.
	 * @param products = product container of an assembly
	 * @param value = id or title of product
	 * @param returnedProduct returned object from recursive
	 * @param flag = detects given value is id or title
	 * @return returnedProuct = found product
	 */
	protected static Product recursiveGetBy(IContainer<Product> products, String value, Product returnedProduct, boolean flag) {
		
		//travel for each product
		for (Product product : products) {
			
			//control of given id or title is equal to gotten product id or title
			if (chooseString(flag,product).equals(value)) {
				returnedProduct = product;
				break;
			}
			
			//If id or title is not equal and gotten product is an assembly, do recursive
			else if(product instanceof Assembly) {
				returnedProduct = recursiveGetBy(((Assembly) product).getProducts(), value,returnedProduct,flag);
				
				//if returnedProduct from recursive is not null and its id/title is equal to gotten id/title break and return it.
				if (returnedProduct != null && chooseString(flag,returnedProduct).equals(value)) {
					break;
				}
			}
		}
		return returnedProduct;
	}
	
	/**
	 * The function is invoked by recursiveGetBy(). According to given flag it returns id or title of given product
	 * @param flag = flag to detect wanted value is title or id
	 * @param product = given product
	 * @return String value
	 */
	private static String chooseString(boolean flag,Product product){
		if(flag)
			return product.getId();
		return product.getTitle();
	}
	
}
