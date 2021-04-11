package fileio.parser;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import contract.Contract;
import factory.CreationResult;
import factory.ICreatorService;
import product.Product;
import storage.ContractContainer;
import storage.IContainer;
import user.User;

public class ContractParser {

	protected ContractParser() {
	}

	protected IContainer<Contract> parseContracts(String fileAll, ICreatorService creator, IContainer<User> users,
			IContainer<Product> products) throws JSONException {
		JSONObject jsonContracts = (new JSONParser()).parse(fileAll); // parse file
		IContainer<Contract> contracts = new ContractContainer(); // init contract container
		parse(jsonContracts, contracts, creator, users, products); // parse contracts
		return contracts;

	}

	private void parse(JSONObject jsonObject, IContainer<Contract> contracts, ICreatorService creator,
			IContainer<User> users, IContainer<Product> products) throws JSONException {

		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))// same as user parser
				throw new JSONException("UserParser.parse::Key is not a string");
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			String valType = val.getClass().getTypeName();
			if (valType.equals("java.lang.String")) { // if key's value string means user-product relation
				String contractor = (String) val;
				Contract newContract = createUserProductContract(key, contractor, users, products, creator);
				contracts.add(newContract);

			} else if (valType.equals("org.json.JSONArray")) { // if key's value array means user-user relation
				JSONArray contractor = (JSONArray) val;
				Contract newContract = createManagerEmployeeContract(contractor, key, users, creator);
				contracts.add(newContract);
			}
		}
	}

	// this function creates user-user contract
	private Contract createManagerEmployeeContract(JSONArray jsonArray, String managerName, IContainer<User> users,
			ICreatorService creator) throws JSONException {
		String[] userId = jsonArray.join(",").replace("\"", "").split(",");// split json array
		CreationResult cr = creator.createContractManagerEmplooye(managerName, userId, users);// pass to creator
		Contract newContract = (Contract) cr.object;
		if (newContract == null)
			throw new JSONException("Wrong format " + cr.message);
		return newContract;
	}

	// this function creates user-product contract
	private Contract createUserProductContract(String userName, String productName, IContainer<User> users,
			IContainer<Product> products, ICreatorService creator) throws JSONException {
		CreationResult cr = creator.createContractUserProduct(userName, productName, users, products);
		Contract newContract = (Contract) cr.object;
		if (newContract == null)
			throw new JSONException("Wrong format " + cr.message);
		return newContract;
	}
}
