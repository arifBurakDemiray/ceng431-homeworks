package model;

import storage.IContainer;

public class CollectionPrint {

	private final String name; // collection name
	private final IContainer<Outfit> ids; // outfit container of collection

	/**
	 * The model for the CollectionPrint which is to show collection in the screen
	 * with its name and outfit ids
	 *
	 */
	public CollectionPrint(Collection collection) {
		this.name = collection.getName();
		this.ids = collection.getOutfits();
	}

	/**
	 * The function returns a string for print
	 * 
	 * @return print string
	 */
	public String toString() {
		return "--- " + this.name + " ---\n" + ids.toString();
	}
}
