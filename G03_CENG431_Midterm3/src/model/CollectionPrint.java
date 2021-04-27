package model;

import storage.IContainer;

public class CollectionPrint {

	private final String name;
	private final IContainer<Outfit> ids;
	
	public CollectionPrint(Collection collection) {
		this.name = collection.getName();
		this.ids= collection.getOutfits();
	}
	
	public String toString(){
		return "--- "+ this.name+" ---\n"+
				ids.toString();
	}
}
