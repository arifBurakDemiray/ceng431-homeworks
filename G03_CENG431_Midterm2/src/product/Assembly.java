package product;

import java.util.ArrayList;
import java.util.Collection;
import state.ProductState;

public class Assembly extends Product {
	public Collection<Product> getProducts() {
		return products;
	}


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
	
	public String toString()
	{
		String thisName = this.getTitle();
		String thisId = this.getId();
		String thisState = this.getProductState().getState();
		String jsonValue = "\""+thisName+"\": {\"id\":\""+thisId+"\",\"state\":\""+thisState+"\",\"type\":\"Part\"";
		jsonValue+=","+this.getProducts().toString()+"}";
		return jsonValue;
		
	}

}
