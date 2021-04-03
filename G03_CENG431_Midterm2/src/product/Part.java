package product;

import state.ProductState;

public class Part extends Product {

	public Part(String id, String title) {
		super(id, title);
	}

	public Part(String id, String title, ProductState state) {
		super(id, title, state);
	}
	
	public String toString()
	{
		return getTitle();
	}

}
