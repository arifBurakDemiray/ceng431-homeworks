package storage;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import product.Product;

public class ProductContainer extends Container<Product> {

	
	//BURAYI ELDEN GECIRMEK LAZIM
	@Override
	public Product getById(String id) throws ItemNotFoundException, NotSupportedException {
		Product returnedUser = null;
		for (Product user : getContainer()) {
			if (user.getId().equals(id)) {

				returnedUser = user;
			}
		}
		if (returnedUser == null) {
			throw new ItemNotFoundException("There is no user has id " + id);
		} else {
			return returnedUser;
		}
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
