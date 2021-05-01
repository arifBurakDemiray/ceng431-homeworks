package fileio.parser;

import factory.ICreatorService;
import model.Outfit;
import model.User;
import storage.IContainer;

public class ContractParam {

	private final IContainer<User> users;
	private final IContainer<Outfit> outfits;
	private final ICreatorService creator;
	
	public ContractParam(IContainer<User> users, IContainer<Outfit> outfits, ICreatorService creator)
	{
		this.users = users;
		this.outfits = outfits;
		this.creator = creator;
	}

	public final IContainer<User> getUsers() {
		return users;
	}

	public final IContainer<Outfit> getOutfits() {
		return outfits;
	}

	public final ICreatorService getCreator() {
		return creator;
	}
	
	


}
