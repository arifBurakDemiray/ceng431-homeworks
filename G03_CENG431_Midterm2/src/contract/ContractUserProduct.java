package contract;

import product.Product;
import user.User;

public class ContractUserProduct extends Contract {

	
	public ContractUserProduct(User usr,Product prd) {
		super(usr,prd);
	}


	
	public String toString() {
		String jsonValue = "\""+((User) getContractee()).getUserName()+"\":\""+((Product) getContracter()).getId()+"\"";
		return jsonValue;
	}
	
}
