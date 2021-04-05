package product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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

	public Collection<Product> getProducts() {
		return products;
	}

	public String toString() {
		String jsonValue = super.toString();
		jsonValue = jsonValue.replace("}", "");
		StringHelper sh = new StringHelper();
		String childs = this.getProducts().toString();
		String[] tokens = { "[", "]" };
		childs = sh.clearOccurences(tokens, childs, "");
		jsonValue += "," + childs + "}";
		return jsonValue;
	}

	@Override
	public void updateState() {

		Collection<Product> temp = this.getProducts();
		String state = this.getProductState();

		Iterator<Product> it = temp.iterator();
		boolean isComplete = true;
		Product product = null;
		while (it.hasNext()) {
			product = it.next();
			if (product instanceof Assembly)
				product.updateState();
			String productState = product.getProductState();
			if (productState.equals("InProgress") && state.equals("NotStarted")) {
				this.getState().nextState();
				isComplete = false;
				break;
			} else if (productState.equals("Complete")) {

			} else
				isComplete = false;
		}
		if (isComplete)
			this.getState().nextState();
	}

}
