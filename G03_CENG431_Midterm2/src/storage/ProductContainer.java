package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import product.Assembly;
import product.Product;

public class ProductContainer extends Container<Product> {

	// BURAYI ELDEN GECIRMEK LAZIM
	@Override
	public Product getById(String id) throws ItemNotFoundException {
		Product returnedProduct = null;
		for (Product prd : getContainer()) {
			if (prd.getId().equals(id)) {

				returnedProduct = prd;
			}
			if (prd instanceof Assembly)
				try {
					returnedProduct = ((Assembly) prd).getProducts().getById(id);}
					catch (NotSupportedException e) {
					e.printStackTrace();
				}
		}
		if (returnedProduct == null) {
			throw new ItemNotFoundException("There is no product has id " + id);
		} else {
			return returnedProduct;
		}
	}

	@Override
	public Product getByName(String name) throws ItemNotFoundException {
		Product found = null;
		for (Product product : this.getContainer()) {
			if (product.equals(name)) {
				found = product; // BURAYA ID CHECK ATMAMIZ LAZIM GELEN PRODUCT AYNIMI DÝYE ÜSTTETE YAPABÝLÝRZ
				break; // ÇAGIRDIFIMIZ YERDE
			}

			if (product instanceof Assembly)
				try {
					found = ((Assembly) product).getProducts().getByName(name);
				} catch (NotSupportedException e) {
					e.printStackTrace();
				}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no product has name " + name);
		} else {
			return found;
		}
	}

}
