package service;

import javax.swing.DefaultListModel;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.OutfitRepository;
import model.Collection;
import model.Outfit;
import storage.IContainer;

public class PopupService {

	
	public PopupService() {
	}
	
	
	public DefaultListModel<String> setOwnedOutfitsList(Collection model) {
		IContainer<Outfit> outfits = model.getOutfits();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Outfit outfit : outfits.getContainer()) {
			listModel.addElement(outfit.getId());
		}
		return listModel;
	}

	public DefaultListModel<String> setOtherOutfitsList(Collection model) {
		OutfitRepository outfitRepository = new OutfitRepository();
		IContainer<Outfit> outfits = outfitRepository.getOutfits();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Outfit outfit : outfits.getContainer()) {
			try {
				model.getOutfits().getById(outfit.getId());
			} catch (ItemNotFoundException | NotSupportedException e) {
				listModel.addElement(outfit.getId());
			}

		}
		return listModel;
	}
}




	
	