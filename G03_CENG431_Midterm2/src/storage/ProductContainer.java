package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import product.Product;

public class ProductContainer extends Container<Product> {

	/**
	 * The function tries to find the product of given product id using recursive.
	 * 
	 * @param String id = given product id
	 * @return Product found product object
	 * @throws ItemNotFoundException, NotSupportedException if product is not found
	 *                                throws ItemNotFoundException, if process is
	 *                                not supported throws NotSupportedException
	 */
	@Override
	public Product getById(String id) throws ItemNotFoundException, NotSupportedException {
		Product returnedProduct = null;
		returnedProduct = StorageHelper.recursiveGetBy(this, id, returnedProduct, true);
		if (returnedProduct == null)
			throw new ItemNotFoundException("There is no product has id " + id);
		return returnedProduct;
	}

	/**
	 * The function tries to find the product of given product name using recursive.
	 * 
	 * @param String name = given product name
	 * @return Product found product object
	 * @throws ItemNotFoundException, NotSupportedException if product is not found
	 *                                throws ItemNotFoundException, if process is
	 *                                not supported throws NotSupportedException
	 */
	@Override
	public Product getByName(String name) throws ItemNotFoundException, NotSupportedException {
		Product returnedProduct = null;
		returnedProduct = StorageHelper.recursiveGetBy(this, name, returnedProduct, false);
		if (returnedProduct == null)
			throw new ItemNotFoundException("There is no product has name " + name);
		return returnedProduct;
	}

}
