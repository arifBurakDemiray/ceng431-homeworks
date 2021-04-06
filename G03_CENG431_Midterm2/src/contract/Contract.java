package contract;

import product.Product;
import user.User;

public class Contract {

	private Product product;
	private User user;
	
	public Contract(Product prd, User usr) {
		this.product = prd;this.user=usr;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return this.product;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}
	
	public String toString() {
		String jsonValue = "\""+this.user.getUserName()+"\":\""+this.product.getId()+"\"";
		return jsonValue;
	}
	
}
