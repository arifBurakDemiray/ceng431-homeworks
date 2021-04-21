package model;

import storage.IContainer;
import storage.OutfitContainer;

public class Collection {


	private String name;
	private IContainer<Outfit> outfits;
	
	public Collection(String name)
	{
		this.name = name;
		outfits = new OutfitContainer();
	}

	public String getName() {
		return name;
	}

	public IContainer<Outfit> getOutfits() {
		return outfits;
	}
	
	
}
