package factory;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Collection;
import model.Outfit;
import storage.IContainer;

public class CreatorHelper {
	
	/**
	 * The function creates a collection model with the given parameters
	 * @param collectionName
	 * @param outfitIds = ids of every outfit in the collection in the string 
	 * @param outfits = container of all outfits to get outfit with id.
	 * @return Collection
	 */
	protected static Collection setCollection(String collectionName, String outfitIds, IContainer<Outfit> outfits) {
		Collection collection = new Collection(collectionName);
		String[] arrayOfOutfiIds = outfitIds.split(","); // split string to get ids

		for (int i = 0; i < arrayOfOutfiIds.length; i++) {
			Outfit outfit = getOutfitWithId(outfits,arrayOfOutfiIds[i]); // find the outfit with given id
			if (outfit != null) {
				collection.getOutfits().add(outfit); // if outfit is found, add Outfit model to the collection.
			}

		}

		return collection;
	}
	
	/**
	 * The function tries to find the outfit of given id
	 * @param outfits = container of all outfits to get outfit with id.
	 * @param outfitId = id of outfit 
	 * @return Outfit 
	 */
	private static Outfit getOutfitWithId(IContainer<Outfit> outfits, String outfitId) {
		Outfit outfit = null;
		try {
			outfit = outfits.getById(outfitId); // If outfit with given id is found, return it.
		} catch (ItemNotFoundException | NotSupportedException e) {
			// System error, id is in the XML but no id found in the outfits container
		}
		return outfit;

	}
	
	
}
