package product;

import state.ProductState;

public abstract class Product {
	private ProductState productState; // holds product state
	private String id;
	private String title;

	/**
	 * The constructor for Product object.
	 * 
	 * @param id           = product id created as unique
	 * @param title        = product title
	 * @param productState = given product state
	 */
	public Product(String id, String title, ProductState productState) {
		this.id = id;
		this.title = title;
		this.productState = productState;
	}

	/**
	 * The constructor for Product object. Product state is defined default as
	 * NotStarted
	 * 
	 * @param id    = product id created as unique
	 * @param title = product title
	 */
	public Product(String id, String title) {
		this.id = id;
		this.title = title;
		this.productState = new ProductState();
	}

	public String getProductState() {
		return this.productState.getState();
	}

	protected ProductState getState() {
		return this.productState;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * The function returns the detail of the product.
	 * 
	 * @return String toString for product
	 */
	public String toString() {
		String thisName = this.getTitle();
		String thisId = this.getId();
		String thisState = this.getProductState();
		String className = this.getClass().getSimpleName();
		String jsonValue = "\"" + thisId + "\": {\"name\":\"" + thisName + "\",\"state\":\"" + thisState
				+ "\",\"type\":\"" + className + "\"}";
		return jsonValue;
	}

	/**
	 * The function controls that given product is equal to that product or not.
	 * 
	 * @param prd = given product
	 * @return boolean of equality
	 */
	public boolean equals(Product prd) {
		String thisName = this.getTitle();
		String thisId = this.getId();
		String prdName = prd.getTitle();
		String prdId = prd.getId();

		// control the variables are equal or not.
		boolean resultName = thisName.equals(prdName);
		boolean resultId = thisId.equals(prdId);
		return resultName && resultId;
	}

	/**
	 * 
	 */
	public boolean equals(String name) {
		String thisName = this.getTitle();
		boolean result = thisName.equals(name);
		return result;
	}

	/**
	 * The function invokes the nextState() function of product state to set the
	 * state as next state.
	 */
	public void updateState() {
		this.productState.nextState();
	}
}
