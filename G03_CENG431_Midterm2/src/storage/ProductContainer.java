package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import product.Product;

public class ProductContainer extends Container<Product> {

	@Override
	public Product getById(String id) throws ItemNotFoundException, NotSupportedException {
		Product returnedProduct = null;
		returnedProduct = StorageHelper.recursiveGetBy(this, id, returnedProduct,true);
		if(returnedProduct==null)
			throw new ItemNotFoundException("There is no product has id " + id);
		return returnedProduct;
	}

	@Override
	public Product getByName(String name) throws ItemNotFoundException, NotSupportedException {
		Product returnedProduct = null;
		returnedProduct = StorageHelper.recursiveGetBy(this, name, returnedProduct,false);
		if(returnedProduct==null)
			throw new ItemNotFoundException("There is no product has name " + name);
		return returnedProduct;
	}

}
