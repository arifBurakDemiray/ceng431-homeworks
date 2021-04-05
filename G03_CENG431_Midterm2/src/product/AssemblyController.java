package product;

public class AssemblyController {

	public AssemblyController() {
		
	}
	
	public boolean addProduct(Assembly mainProduct, Product productToAdd) {
		boolean result = mainProduct.addProduct(productToAdd);
		return result;
	}

	public boolean removeProduct(Assembly mainProduct,Product product) {
		boolean result = mainProduct.removeProduct(product);
		return result;
	}
	
}
