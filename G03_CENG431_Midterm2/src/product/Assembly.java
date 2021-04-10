package product;

import java.util.Iterator;

import exception.ItemNotFoundException;
import state.ProductState;
import storage.IContainer;
import storage.ProductContainer;

public class Assembly extends Product {

	private IContainer<Product> products;

	public Assembly(String id, String title) {
		super(id, title);
		this.products = new ProductContainer();
	}

	public Assembly(String id, String title, ProductState state) {
		super(id, title, state);
		this.products = new ProductContainer();
	}

	public boolean addProduct(Product product) {
		boolean result = this.products.add(product);
		return result;
	}

	public boolean removeProduct(Product product) {
		try {
			Product tempProduct = this.products.remove(product);
			if(tempProduct.equals(product))
				return true;
			return false;
		} catch (ItemNotFoundException e) {
			return false;
		}

	}

	public IContainer<Product> getProducts() {
		return products;
	}

	public String toString() {
		String jsonValue = super.toString();
		jsonValue = jsonValue.replace("}", "");
		String childs = this.getProducts().toString();
		if(childs.equals(""))
			jsonValue+="}";
		else
			jsonValue += "," + childs+"}";
		return jsonValue;
	}

	@Override
	public void updateState() {
		String state = this.getProductState();
		IContainer<Product> temp = this.getProducts();
		Iterator<Product> it = temp.iterator();
		boolean isComplete = true;
		Product product = null;
		if(!it.hasNext())
			return;
		while (it.hasNext()) {
			product = it.next();
			if (product instanceof Assembly)
				product.updateState();
			String productState = product.getProductState();
			if (productState.equals("InProgress") && state.equals("NotStarted")) {
				this.getState().nextState();
				isComplete = false;
				break;
			} 
			else if((productState.equals("InProgress") || productState.equals("NotStarted")) && state.equals("Completed") ) {
				this.getState().backState();
				isComplete = false;
				break;
			}
			else if (productState.equals("Completed")) {

 			} else
				isComplete = false;
		}
		if (isComplete) {
			this.getState().nextState();
			if(this.getProductState().equals("InProgress"))
				this.getState().nextState();		
	}
	}
}
