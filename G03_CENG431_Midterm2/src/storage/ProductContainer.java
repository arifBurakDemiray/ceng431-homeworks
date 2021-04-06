package storage;

import java.util.Iterator;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import product.Assembly;
import product.Product;

public class ProductContainer extends Container<Product> {

	// BURAYI ELDEN GECIRMEK LAZIM
	@Override
	public Product getById(String id) throws ItemNotFoundException, NotSupportedException {
		Product returnedProduct = null;
		boolean isContinueTrue = true;
		Iterator<?> productsIterator = this.getContainer().iterator();
		while(isContinueTrue && productsIterator.hasNext() && returnedProduct == null)
		{
			Product mainProduct = (Product) productsIterator.next();
			System.out.println("Main product Search: " + mainProduct.toString());
			if(mainProduct.getId().equals(id))
			{
				returnedProduct = mainProduct;
				System.out.println("FOUND : "+returnedProduct.toString());
				isContinueTrue = false;
				break;				
			}
			
			else
			{
				returnedProduct = StorageHelper.recursiveGetById(((Assembly) mainProduct).getProducts(), id, returnedProduct);
				if(returnedProduct.getId().equals(id))
				{
					isContinueTrue = false;
					break;					
				}
			}			
		}
		
		
		if(returnedProduct!=null)
		{
			return returnedProduct;
		}
		else
		{
			throw new ItemNotFoundException("There is no item with ID "+id);
		}
		
		
	}

	@Override
	public Product getByName(String name) throws ItemNotFoundException, NotSupportedException {
		Product found = null;
		for (Product product : this.getContainer()) {
			if (product.equals(name)) {
				found = product; // BURAYA ID CHECK ATMAMIZ LAZIM GELEN PRODUCT AYNIMI DÝYE ÜSTTETE YAPABÝLÝRZ
				break; // ÇAGIRDIFIMIZ YERDE
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no product has name " + name);
		} else {
			return found;
		}
	}

}
