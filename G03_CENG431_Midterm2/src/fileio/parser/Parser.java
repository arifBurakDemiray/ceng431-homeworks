package fileio.parser;

import org.json.JSONException;

import contract.Contract;
import factory.ICreatorService;
import product.Product;
import storage.IContainer;
import user.User;

/**
 * This function is a middleware for parser classes
 *
 */
public class Parser {
	private UserParser userParser;
	private ProductParser productParser;
	private ContractParser contractParser;

	public Parser() {
		this.contractParser = new ContractParser();
		this.productParser = new ProductParser();
		this.userParser = new UserParser();
	}

	public IContainer<User> parseUsers(String fileAll, ICreatorService creator) throws JSONException {
		return userParser.parseUsers(fileAll, creator);
	}

	public IContainer<Product> parseProducts(String fileAll, ICreatorService creator) throws JSONException {
		return productParser.parseProducts(fileAll, creator);
	}

	public IContainer<Contract> parseContracts(String fileAll, ICreatorService creator, IContainer<User> users,
			IContainer<Product> products) throws JSONException {
		return contractParser.parseContracts(fileAll, creator, users, products);
	}
}
