package fileio.parser;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import contract.Contract;
import factory.CreationResult;
import factory.Creator;
import product.Product;
import storage.ContractContainer;
import storage.IContainer;
import user.User;

public class ContractParser {

	public IContainer<Contract> parseContracts(String fileAll, Creator creator, IContainer<User> users,
			IContainer<Product> products) throws JSONException {
		JSONObject jsonContracts = (new JSONParser()).parse(fileAll);
		IContainer<Contract> contracts = new ContractContainer();
		parse(jsonContracts, contracts, creator, users, products);
		return contracts;

	}

	private void parse(JSONObject jsonObject, IContainer<Contract> contracts, Creator creator, IContainer<User> users,
			IContainer<Product> products) throws JSONException {

		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("UserParser.parse::Key is not a string");
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			String valType = val.getClass().getTypeName();
			if (valType.equals("java.lang.String")) {
				String contractor = (String) val;
				Contract newContract = createUserProductContract(key,contractor,users,products,creator);
				contracts.add(newContract);

			} else if (valType.equals("org.json.JSONArray")) {
				JSONArray contractor = (JSONArray) val;
				Contract newContract = createManagerEmployeeContract(contractor,key,users,creator);
				contracts.add(newContract);
			}
		}
	}
	
	private Contract createManagerEmployeeContract(JSONArray jsonArray, String managerName,IContainer<User> users,Creator creator) throws JSONException{
		String[] userId = jsonArray.join(",").replace("\"","").split(",");
		CreationResult cr = creator.createContractManagerEmplooye(managerName, userId, users);
		Contract newContract = (Contract) cr.object;
		if (newContract == null)
			throw new JSONException("Wrong format " + cr.message);
		return newContract;
	}
	
	private Contract createUserProductContract(String userName,String productName, IContainer<User> users, IContainer<Product> products,Creator creator) throws JSONException{
		CreationResult cr = creator.createContractUserProduct(userName, productName, users, products);
		Contract newContract = (Contract) cr.object;
		if (newContract == null)
			throw new JSONException("Wrong format " + cr.message);
		return newContract;
	}
}
