package product;

import state.ProductState;

public class Part extends Product {

	/**
	 * The constructor to create Part object
	 * @param id = product id
	 * @param title = product title
	 */
	public Part(String id, String title) {
		super(id, title);
	}
	
	/**
	 * The constructor to create Part object
	 * @param id = product id
	 * @param title = product title
	 * @param state = product state
	 */
	public Part(String id, String title, ProductState state) {
		super(id, title, state);
	}
	


}
