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
		String thisName = this.getTitle();
		String thisId = this.getId();
		String thisState = this.getProductState().getState();
		String jsonValue = "\""+thisName+"\": {\"id\":\""+thisId+"\",\"state\":\""+thisState+"\",\"type\":\"Part\"}";
		return jsonValue;
	}

}
