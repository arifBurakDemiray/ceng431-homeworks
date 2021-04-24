package factory;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Collection;
import model.Outfit;
import storage.IContainer;

public class CreatorHelper {

	protected static Collection setCollection(String collectionName, String outfitIds, IContainer<Outfit> outfits) {
		Collection collection = new Collection(collectionName);
		String[] arrayOfOutfiIds = outfitIds.split(",");

		for (int i = 0; i < arrayOfOutfiIds.length; i++) {
			Outfit outfit = getOutfitWithId(outfits,arrayOfOutfiIds[i]);
			if (outfit != null) {
				collection.getOutfits().add(outfit);
			}

		}

		return collection;
	}

	private static Outfit getOutfitWithId(IContainer<Outfit> outfits, String outfitId) {
		Outfit outfit = null;
		try {
			outfit = outfits.getById(outfitId);
		} catch (ItemNotFoundException | NotSupportedException e) {
			// System error, id is in the XML but no id found in the outfits container
		}
		return outfit;

	}
	
	
}
