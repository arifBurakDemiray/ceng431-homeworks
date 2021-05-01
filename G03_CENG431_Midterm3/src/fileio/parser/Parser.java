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

	public IContainer<User> parseUsers(String fileAll, ICreatorService creator,IContainer<Outfit> outfits ) throws Exception {
		return userParser.parseUsers(fileAll, creator, outfits);
	}

	public IContainer<Outfit> parseOutfits(String fileAll, ICreatorService creator) throws JSONException {
		return outfitParser.parseOutfits(fileAll, creator);
	}
	
	public IContainer<Contract> parseContracts(String fileAll, ICreatorService creator, IContainer<User> users,
			IContainer<Outfit> outfits) throws JSONException {
		return contractParser.parseContracts(fileAll, creator, users, outfits);
	}

}
