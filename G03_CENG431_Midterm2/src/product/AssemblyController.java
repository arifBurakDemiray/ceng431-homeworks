package product;

public class AssemblyController {

	public AssemblyController() {

	}

	/**
	 * The function adds product to the given assembly.
	 * 
	 * @param mainProduct  = assembly product
	 * @param productToAdd = product to add inside to mainProduct
	 * @return boolean of process
	 */
	public boolean addProduct(Assembly mainProduct, Product productToAdd) {
		boolean result = mainProduct.addProduct(productToAdd);
		return result;
	}

	/**
	 * The function removes product from the given assembly.
	 * 
	 * @param mainProduct  = assembly product
	 * @param productToAdd = product to remove from inside of mainProduct
	 * @return boolean of process
	 */
	public boolean removeProduct(Assembly mainProduct, Product product) {
		boolean result = mainProduct.removeProduct(product);
		return result;
	}

}
