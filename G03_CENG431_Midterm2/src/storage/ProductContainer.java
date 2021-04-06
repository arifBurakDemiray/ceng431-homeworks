package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import product.Assembly;
import product.Product;

public class ProductContainer extends Container<Product> {

	
	//BURAYI ELDEN GECIRMEK LAZIM
	@Override
	public Product getById(String id) throws ItemNotFoundException, NotSupportedException {
		Product returnedProduct = null;
		returnedProduct = StorageHelper.recursiveGetById(this, id, returnedProduct);
		if(returnedProduct==null)
			throw new ItemNotFoundException("There is no product has id " + id);
		return returnedProduct;
	}

	@Override
	public Product getByName(String name) throws ItemNotFoundException, NotSupportedException {
		Product found = null;
		for (Product product : this.getContainer()) {
			if (product.equals(name)) {
				found = product;  //BURAYA ID CHECK ATMAMIZ LAZIM GELEN PRODUCT AYNIMI DÝYE ÜSTTETE YAPABÝLÝRZ
				break;          //ÇAGIRDIFIMIZ YERDE
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no product has name " + name);
		} else {
			return found;
		}
	}

}
