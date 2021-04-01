package product;

import state.ProductState;

public abstract class Product {
	private ProductState productState;
	private String id;
	private String title;

	public Product(String id, String title, ProductState productState) {
		this.id = id;
		this.title = title;
		this.productState = productState;
	}

	public Product(String id, String title) {
		this.id = id;
		this.title = title;
		this.productState = new ProductState();
	}

	public ProductState getProductState() {
		return productState;
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
}
