package model;

import storage.IContainer;
import storage.OutfitContainer;
import observation.Observable;

public class Collection extends Observable {

	private String name; // collection name
	private IContainer<Outfit> outfits; // outfit container which holds outfits of collection

	/**
	 * The model for the Collection
	 *
	 */
	public Collection(String name) {
		this.name = name;
		outfits = new OutfitContainer();
	}

	/**
	 * The function returns collection name
	 * 
	 * @return collection name
	 */
	public String getName() {
		return name;
	}

	/**
	 * The function returns outfit container
	 * 
	 * @return outfit container
	 */
	public IContainer<Outfit> getOutfits() {
		return outfits;
	}

	/**
	 * The function is returns a string of collection with xml type for users.xml
	 * 
	 * @return string of collection
	 */
	public String toString() {
		return "\t\t\t<collection name=\"" + this.getName() + "\">\n\t" + "\t\t\t<ids>" + this.outfitsToString()
				+ "</ids>\n\t\t\t</collection>\n";
	}

	/**
	 * The function is a helper function for toString() which returns a string of
	 * outfits as "65,38,22"
	 * 
	 * @return string of outfits
	 */
	private String outfitsToString() {
		String result = "";
		IContainer<Outfit> outfits = this.getOutfits();
		if (outfits.isEmpty())
			return result;
		for (Outfit outfit : outfits) {
			result += outfit.getId() + ",";
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * The function controls that gotten collection name is equal to the that
	 * collection's name
	 * 
	 * @param collectionName = gotten collection name
	 * @return boolean
	 */
	public boolean equals(String collectionName) {
		return this.getName().equals(collectionName);
	}
}
