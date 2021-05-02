package fileio.parser;

import factory.ICreatorService;
import model.Outfit;
import model.User;
import storage.IContainer;

public class ContractParam {

	private final IContainer<User> users; // holds user container of system which holds all users
	private final IContainer<Outfit> outfits; // holds outfit container of system which holds all outfits
	private final ICreatorService creator; // holds creator object

	/**
	 * The ContractParam is an object which includes necessary parameters for
	 * ContractParser functions.
	 * 
	 * @param users   = user container of system which holds all users
	 * @param outfits = outfit container of system which holds all outfits
	 * @param creator = creator object
	 */
	public ContractParam(IContainer<User> users, IContainer<Outfit> outfits, ICreatorService creator) {
		this.users = users;
		this.outfits = outfits;
		this.creator = creator;
	}

	/**
	 * The function returns the user container
	 * 
	 * @return user container
	 */
	public final IContainer<User> getUsers() {
		return users;
	}

	/**
	 * The function returns the outfit container
	 * 
	 * @return outfit container
	 */
	public final IContainer<Outfit> getOutfits() {
		return outfits;
	}

	/**
	 * The function returns the creator object
	 * 
	 * @return creator object
	 */
	public final ICreatorService getCreator() {
		return creator;
	}

}
