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

	/**
	 * The ContractParser parses the gotten contract file content and creates
	 * contract objects
	 */
	protected ContractParser() {
	}

	/**
	 * The function parses gotten file content and returns the contract container
	 * which holds created contracts
	 * 
	 * @param fileAll = contract file content
	 * @param creator = creator object
	 * @param users   = user container which holds all users
	 * @param outfits = outfit container which holds all outfits
	 * @return Contract Container
	 * @throws JSONException
	 */
	protected IContainer<Contract> parseContracts(String fileAll, ICreatorService creator, IContainer<User> users,
			IContainer<Outfit> outfits) {

		IContainer<Contract> contracts = null; // contract container
		try {
			JSONObject jsonContracts;
			jsonContracts = (new JSONParser()).parse(fileAll); // get json object of file content
			contracts = new ContractContainer(); // initialise contract container
			parse(jsonContracts, contracts, new ContractParam(users, outfits, creator)); // parse contracts
		} catch (JSONException e) {
		}

		return contracts;

	}

	/**
	 * The function parses gotten jsonObject and creates contract. After creating
	 * each contract, add the contract to the contracts
	 * 
	 * @param jsonObject    = json object of file content
	 * @param contracts     = contract container
	 * @param contractParam = ContractParam object which holds necessary parameters
	 *                      as object
	 * @throws JSONException
	 */
	private void parse(JSONObject jsonObject, IContainer<Contract> contracts, ContractParam contractParam)
			throws JSONException {

		// iterate json object
		Iterator<?> keys = jsonObject.keys();
		Object val = null;

		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))// same as user parser
				throw new JSONException("UserParser.parse::Key is not a string");
			// get the contract values and invoke createContractUserOutfitsLikes()
			// to get created contract
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			if (val.getClass().getSimpleName().equals("String")) {
				Contract contract = createContractUserOutfitsLikes(key, (String) val, contractParam);
				contracts.add(contract); // add the contract to the contract container
			}
		}
	}

	/**
	 * The function creates a contract between user and outfits
	 * 
	 * @param userName      = user name
	 * @param outfitsId     = outfits id like "68,35,22"
	 * @param contractParam = ContractParam object which holds necessary parameters
	 *                      as object
	 * @return Contract
	 * @throws JSONException
	 */
	private Contract createContractUserOutfitsLikes(String userName, String outfitsId, ContractParam contractParam)
			throws JSONException {
		final var creator = contractParam.getCreator(); // get creator object from contractParam
		// Try to create contract using creator
		CreationResult cr = creator.createContractUserOutfitsLikes(userName, outfitsId, contractParam);
		Contract newContract = (Contract) cr.object;
		if (newContract == null)
			throw new JSONException("Wrong format " + cr.message);
		return newContract; // If creation is successful, return contract.

	}

}
