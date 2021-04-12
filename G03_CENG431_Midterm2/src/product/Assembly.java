package product;

import java.util.Iterator;

import exception.ItemNotFoundException;
import state.ProductState;
import storage.IContainer;
import storage.ProductContainer;

public class Assembly extends Product {

	private IContainer<Product> products; // holds products ( parts and assemblies ) of the assembly

	/**
	 * The constructor to create Assembly object
	 * 
	 * @param id    = product id
	 * @param title = product title
	 */
	public Assembly(String id, String title) {
		super(id, title);
		this.products = new ProductContainer();
	}

	/**
	 * The constructor to create Assembly object
	 * 
	 * @param id    = product id
	 * @param title = product title
	 * @param state = product state
	 */
	public Assembly(String id, String title, ProductState state) {
		super(id, title, state);
		this.products = new ProductContainer();
	}

	/**
	 * The functions adds the given product to the assembly's product container
	 * invoking IContainer add() function
	 * 
	 * @param product given product
	 * @return boolean result of process
	 */
	public boolean addProduct(Product product) {
		boolean result = this.products.add(product);
		return result;
	}

	/**
	 * The functions tries to remove the given product from the assembly's product
	 * container invoking IContainer remove() function
	 * 
	 * @param product given product
	 * @return boolean result of process
	 */
	public boolean removeProduct(Product product) {
		try {
			Product tempProduct = this.products.remove(product);
			if (tempProduct.equals(product))
				return true;
			return false;
		} catch (ItemNotFoundException e) {
			return false;
		}

	}

	/**
	 * The function returns the detail of the product.
	 * 
	 * @return String toString for product
	 */
	public String toString() {
		String jsonValue = super.toString();
		jsonValue = jsonValue.replace("}", "");
		String childs = this.getProducts().toString();
		if (childs.equals(""))
			jsonValue += "}";
		else
			jsonValue += "," + childs + "}";
		return jsonValue;
	}

	/**
	 * The function recursively iterates the assemblies inner products and control
	 * each product's state. According to situation assembly updates its state
	 */
	@Override
	public void updateState() {
		String state = this.getProductState();
		IContainer<Product> temp = this.getProducts();
		Iterator<Product> it = temp.iterator();
		boolean isComplete = true;
		Product product = null;

		if (!it.hasNext())
			return;

		// Iterate in the assembly product as recursive
		while (it.hasNext()) {
			product = it.next();
			// if gotten product is assembly, do recursive
			if (product instanceof Assembly)
				product.updateState();
			String productState = product.getProductState();

			// When a product inside the assembly is in progress and the assembly is not
			// started, update assembly's state as inProgress.
			if (productState.equals("InProgress")  && state.equals("NotStarted")) {
				this.getState().nextState();
				isComplete = false;
				break;
			}

			// When a product inside the assembly is in progress or not started and the
			// assembly is completed, update assembly's state as inProgress.
			// That situation is for that when assembly is completed but you want to add a
			// new product to assembly, it updates assembly's state.
			else if ((productState.equals("InProgress") || productState.equals("NotStarted"))
					&& state.equals("Completed")) {
				this.getState().backState();
				isComplete = false;
				break;
			}

			// If a product directly set to completed but just only one of them completed
			// not all
			// our main product should be InProgress but our main product should has been
			// NotStarted
			else if (productState.equals("Completed") && state.equals("NotStarted")) {
				this.getState().nextState();
			} else if (productState.equals("Completed")) {
			}

			else
				isComplete = false;
		}

		// If all the products inside the assembly is completed, make the assembly
		// status completed.
		if (isComplete) {
			this.getState().nextState();
			if (this.getProductState().equals("InProgress"))
				this.getState().nextState();
		}
	}

	public IContainer<Product> getProducts() {
		return products;
	}

}
