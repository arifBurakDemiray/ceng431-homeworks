package service;

import fileio.DatabaseResult;
import fileio.OutfitRepository;
import model.Outfit;

public class HomeService {

	private final OutfitRepository outfits;

	public HomeService() {
		outfits = new OutfitRepository();
	}

	/**
	 * This function takes a outfit name and gets it from repository
	 * 
	 * @param selectedItemName wanted outfit name
	 * @return outfit, if not found null
	 */
	public Outfit getSelectedOutfit(String selectedItemName) {
		Outfit result = null;
		if (selectedItemName!=null && selectedItemName.contains("Outfit")) {// this shows up this is an outfit item
			DatabaseResult dbResult = outfits.getOutfitById(selectedItemName.split("-")[1]);// take id of it
			Object dbObject = dbResult.getObject();
			if (dbObject != null) { // if result not null
				result = (Outfit) dbObject;
			}
		}
		return result;
	}
}