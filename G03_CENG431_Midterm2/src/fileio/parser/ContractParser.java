package fileio.parser;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import contract.Contract;
import contract.ContractManagerEmployee;
import contract.ContractUserProduct;
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
				String contractee = key;
				CreationResult cr = creator.createContractUserProduct(contractee, contractor, users, products);
				Contract newContract = (ContractUserProduct) cr.object;
				if (newContract == null)
					throw new JSONException("Wrong format " + cr.message);
				contracts.add(newContract);

			} else if (valType.equals("org.json.JSONArray")) {
				JSONArray contractor = (JSONArray) val;
				String[] userId = contractor.join(",").replace("\"","").split(",");
				String contractee = key;
				CreationResult cr = creator.createContractManagerEmplooye(contractee, userId, users);
				Contract newContract = (ContractManagerEmployee) cr.object;
				if (newContract == null)
					throw new JSONException("Wrong format " + cr.message);
				contracts.add(newContract);
			}
		}
	}
}
