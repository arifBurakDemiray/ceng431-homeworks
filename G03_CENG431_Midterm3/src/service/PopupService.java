package service;

import javax.swing.DefaultListModel;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.DatabaseResult;
import fileio.OutfitRepository;
import model.CollectionReview;
import model.Outfit;
import storage.IContainer;

/**
 * This class handles database communication with controller and also calculates
 * updated lists
 */
public class PopupService {

	private final OutfitRepository outfitRepository;

	public PopupService() {
		outfitRepository = new OutfitRepository();
	}

	/**
	 * This function renders collection's outfits
	 * 
	 * @param model collection that is going to be rendered
	 * @return rendered list
	 */
	public DefaultListModel<String> setOwnedOutfitsList(CollectionReview model) {
		IContainer<Outfit> outfits = model.getOutfits();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Outfit outfit : outfits) {
			String outfitDetails = (outfit.getId() + ":" + outfit.getType());
			listModel.addElement(outfitDetails); // add collection's outfits
		}
		return listModel;
	}

	/**
	 * This function renders collection's not owned outfits
	 * 
	 * @param model collection that is going to be rendered
	 * @return rendered list
	 */
	public DefaultListModel<String> setOtherOutfitsList(CollectionReview model) {
		IContainer<Outfit> outfits = outfitRepository.getOutfits();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Outfit outfit : outfits) {
			try {
				model.getOutfits().getById(outfit.getId()); // if collection has this outfit not add to list
			} catch (ItemNotFoundException | NotSupportedException e) {
				String outfitDetails = (outfit.getId() + ":" + outfit.getType());
				listModel.addElement(outfitDetails);
			}

		}
		return listModel;
	}

	/**
	 * This function removes an outfit from collection
	 * 
	 * @param outfitDetail the removed outfit
	 * @param model        collection that has the outfit
	 */
	public void removeOutfitFromCollection(String outfitDetail, CollectionReview model) {
		Outfit outfit = null;
		if(outfitDetail!=null && !outfitDetail.equals(""))
		{
			String outfitId = outfitDetail.split(":")[0];
			try {
				outfit = model.getOutfits().getById(outfitId);
			} catch (ItemNotFoundException | NotSupportedException e) {
			}
		}
		
		if (outfit != null) // if collection has outfit
			removeOutfit(outfit, model);
	}

	/**
	 * This function add an outfit to collection
	 * 
	 * @param outfitDetail the added outfit
	 * @param model        collection that is going to has outfit
	 */
	public void addOutfitToCollection(String outfitDetail, CollectionReview model) {
		
		if(outfitDetail != null && !outfitDetail.equals(""))
		{
			String outfitId = outfitDetail.split(":")[0];
			DatabaseResult databaseResult = outfitRepository.getOutfitById(outfitId);
			Outfit selectedOutfit = (Outfit) databaseResult.getObject();
			if (selectedOutfit != null) { // if outfit successfully found
				addOutfit(selectedOutfit, model);
			}
		}
		
	}

	// this function simply adds outfit to collection
	public void addOutfit(Outfit outfit, CollectionReview model) {
		boolean result = model.getOutfits().add(outfit);
		if (result) // if added save
			outfitRepository.saveChanges();

	}

	// this function simply removes outfit from collection
	public void removeOutfit(Outfit outfit, CollectionReview model) {
		Object removed = null;
		try {
			removed = model.getOutfits().remove(outfit);
		} catch (ItemNotFoundException e) {
		}
		if (removed != null) // if removed save
			outfitRepository.saveChanges();
	}

	/**
	 * This function gets an outfits by its id
	 * 
	 * @param outfitId selected outfitId
	 * @return if found returns found outfit, otherwise null
	 */
	public Outfit getOutfitById(String outfitId) {
		DatabaseResult databaseResult = outfitRepository.getOutfitById(outfitId);
		return (Outfit) databaseResult.getObject();
	}

}
