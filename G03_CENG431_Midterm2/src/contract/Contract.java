package contract;


public class Contract {

	private String productId;
	private String userName;
	
	public Contract(String prd, String usr) {
		this.productId = prd;this.userName=usr;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return this.productId;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return this.userName;
	}
	
	public String toString() {
		String jsonValue = "\""+this.userName+"\":\""+this.productId+"\"";
		return jsonValue;
	}
	
}
