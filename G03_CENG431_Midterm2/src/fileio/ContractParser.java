package fileio;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import contract.Contract;
import factory.Creator;
import storage.ContractContainer;
import storage.IContainer;

public class ContractParser {
	
	public IContainer<Contract> parseContracts(String fileAll, Creator creator) throws JSONException {
		JSONObject jsonContracts = (new JSONParser()).parse(fileAll);
		IContainer<Contract> contracts = new ContractContainer();
		parse(jsonContracts, contracts, creator);
		return contracts;

	}

	private void parse(JSONObject jsonObject, IContainer<Contract> contracts, Creator creator)
			throws JSONException {
		// TYPE CAST EXCEPTION BAK
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				// throw new JSONException(""); //Type cast bura galiba //eski olan
				throw new JSONException("UserParser.parse::Key is not a string"); // Yeni olan
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			String valType = val.getClass().getTypeName();
			if (valType.equals("java.lang.String")) {
				String productId = (String) val;
				String userName = key;
				Contract contract = new Contract(productId,userName);
				contracts.add(contract);

			}
		}
	}
}
