package fileio.parser;

import org.json.JSONException;

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

	public Parser() {
		this.outfitParser = new OutfitParser();
		this.userParser = new UserParser();
	}

	public IContainer<User> parseUsers(String fileAll, ICreatorService creator) throws Exception {
		return userParser.parseUsers(fileAll, creator);
	}

	public IContainer<Outfit> parseOutfits(String fileAll, ICreatorService creator) throws JSONException {
		return outfitParser.parseOutfits(fileAll, creator);
	}

}
