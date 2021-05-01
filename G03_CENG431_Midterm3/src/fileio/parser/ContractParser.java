package fileio.parser;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import contract.Contract;
import factory.CreationResult;
import factory.ICreatorService;
import storage.ContractContainer;
import storage.IContainer;
import model.Outfit;
import model.User;

public class ContractParser {

	protected ContractParser() {
	}

	protected IContainer<Contract> parseContracts(String fileAll,ICreatorService creator,IContainer<User> users, IContainer<Outfit> outfits) {

		IContainer<Contract> contracts = null; // init contract container
		try {
			JSONObject jsonContracts;
			jsonContracts = (new JSONParser()).parse(fileAll);
			contracts = new ContractContainer(); // init contract container
			parse(jsonContracts, contracts, new ContractParam(users, outfits, creator)); // parse contracts
		} catch (JSONException e) {
		} // parse file

		return contracts;

	}

	private void parse(JSONObject jsonObject, IContainer<Contract> contracts, ContractParam contractParam) throws JSONException {

		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))// same as user parser
				throw new JSONException("UserParser.parse::Key is not a string");
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			if (val.getClass().getSimpleName().equals("String")) {		
				Contract contract = createContractUserOutfitsLikes(key, (String) val, contractParam);
				contracts.add(contract);
			}
		}
	}

	// this function creates user-user contract
	private Contract createContractUserOutfitsLikes( String userName, String outfitsId,
			 ContractParam contractParam) throws JSONException  {
		final var creator = contractParam.getCreator();
		CreationResult cr = creator.createContractUserOutfitsLikes(userName, outfitsId, contractParam);
		Contract newContract = (Contract) cr.object;
		if (newContract == null)
			throw new JSONException("Wrong format " + cr.message);
		return newContract;

	}


}
