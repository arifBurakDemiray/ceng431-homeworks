package storage;

import java.util.Collection;
import java.util.Iterator;

import product.Assembly;
import product.Product;


public class StorageHelper {

	protected StorageHelper() {
		
		
	}
	protected static Product recursiveGetById(IContainer<Product> products,String id,Product returnedProduct)  {
		boolean isContinueTrue = true;
		Iterator<?> productIterator = products.getContainer().iterator();
		while(isContinueTrue && productIterator.hasNext() && returnedProduct == null)
		{
			Product product = (Product) productIterator.next();
			System.out.println("Search: " + product.toString());
			if (product.getId().equals(id)) {
				returnedProduct = product;
				System.out.println("FOUND : "+returnedProduct.toString());
				isContinueTrue = false;
				break;
			}
			if(product instanceof Assembly) {				
				recursiveGetById(((Assembly) product).getProducts(), id,returnedProduct);
			}			
		}				
		return returnedProduct;
	}
}
