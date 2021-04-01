package product;

import java.util.ArrayList;
import java.util.Collection;
import state.ProductState;

public class Assembly extends Product {
	private Collection<Product> products;

	public Assembly(String id, String title) {
		super(id, title);
		this.products = new ArrayList<Product>();
	}

	public Assembly(String id, String title, ProductState state) {
		super(id, title, state);
		this.products = new ArrayList<Product>();
	}

	public boolean addProduct(Product product) {
		boolean result = this.products.add(product);
		return result;
	}

	public boolean removeProduct(Product product) {
		boolean result = this.products.remove(product);
		return result;
	}

}
