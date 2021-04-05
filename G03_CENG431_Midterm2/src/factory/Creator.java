package factory;

import product.Assembly;
import product.Part;
import product.Product;
import state.ProductState;
import state.*;

public class Creator {

	private final Validator validator;
	
	public Creator() {
		validator = new Validator();
	}
	
	public Product createProduct(String type,String title,String id,String state) {
		boolean result = validator.validateProduct(id,type);
		Product prd = null;
		if(result) {
			ProductState prdSt = createProductState(state);
			if(!prdSt.equals(null)) {
				switch(type)
				{
				case "Assembly":
					prd = new Assembly(id,title,prdSt);
					break;
				case "Part":
					prd = new Part(id,title,prdSt);
					break;
				}
			}
		}
		return prd;
	}
	
	private ProductState createProductState(String state) {
		boolean result = validator.validateState(state);
		State st = null;
		ProductState pst = null;
		if(result) {
			switch(state) {
			case "NotStarted":
				st = new NotStarted();
				break;
			case "InProgress":
				st = new InProgress();
				break;
			case "Completed":
				st = new Completed();
				break;
			}
			pst = new ProductState(st);
		}
		return pst;
	}
}
