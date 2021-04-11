package contract;

import product.Product;
import user.User;

/**
 * This class represents contract between an user and a product
 *
 */
public class ContractUserProduct extends Contract {

	public ContractUserProduct(User usr, Product prd) {
		super(usr, prd);
	}

	// This function converts a contract to string
	public String toString() {// it modified to write in a json file
		String jsonValue = "\"" + ((User) getContractee()).getUserName() + "\":\"" + ((Product) getContracter()).getId()
				+ "\"";
		return jsonValue;
	}

}
