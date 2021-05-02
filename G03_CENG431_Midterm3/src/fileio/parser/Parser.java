package fileio.parser;

import org.json.JSONException;

import contract.Contract;
import factory.ICreatorService;
import model.Outfit;
import model.User;
import storage.IContainer;

/**
 * This function is a middleware for parser classes
 *
 */
public class Parser {
	private UserParser userParser;
	private OutfitParser outfitParser;
	private ContractParser contractParser;

	public Parser() {
		this.outfitParser = new OutfitParser();
		this.userParser = new UserParser();
		this.contractParser = new ContractParser();
	}

	/**
	 * The function parses gotten file content and returns the user container which
	 * holds created users
	 * 
	 * @param fileAll = user file content
	 * @param creator = creator object
	 * @return User Container
	 * @throws XMLException
	 */
	public IContainer<User> parseUsers(String fileAll, ICreatorService creator, IContainer<Outfit> outfits)
			throws Exception {
		return userParser.parseUsers(fileAll, creator, outfits);
	}

	/**
	 * The function parses gotten file content and returns the outfit container
	 * which holds created outfits
	 * 
	 * @param fileAll = outfit file content
	 * @param creator = creator object
	 * @return Outfit Container
	 * @throws JSONException
	 */
	public IContainer<Outfit> parseOutfits(String fileAll, ICreatorService creator) throws JSONException {
		return outfitParser.parseOutfits(fileAll, creator);
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
	public IContainer<Contract> parseContracts(String fileAll, ICreatorService creator, IContainer<User> users,
			IContainer<Outfit> outfits) throws JSONException {
		return contractParser.parseContracts(fileAll, creator, users, outfits);
	}

}
