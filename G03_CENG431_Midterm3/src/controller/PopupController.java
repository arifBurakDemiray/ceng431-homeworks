package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import enums.ButtonState;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.DatabaseResult;
import fileio.OutfitRepository;
import model.Collection;
import model.Outfit;
import model.UpdatedList;
import observation.Observable;
import observation.Observer;
import service.PopupService;
import view.PopupView;

public class PopupController {

	private Observable model;
	private Observer view;
	private PopupService service;
	private OutfitRepository outfitRepository;

	public PopupController(Observable model, Observer view) {
		this.model = (Collection) model;
		this.view = view;
		model.addObserver(view);
		service = new PopupService();
		outfitRepository = new OutfitRepository();
		((PopupView) this.view).addRemoveButtonListener(new RemoveButtonListener());
		((PopupView) this.view).addAddButtonListener(new AddButtonListener());
		updateLists();
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify(ButtonState.BACK_BUTTON);
			model.removeObserver(view);
		}
	}

	
	class RemoveButtonListener implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedOutfitId = ((PopupView) view).getSelectedOutfitToRemove();
			
			Outfit outfit = null;
			try {
				outfit = ((Collection)model).getOutfits().getById(selectedOutfitId);	
				removeOutfit(outfit);	
			} catch (ItemNotFoundException | NotSupportedException e1) {
			}		
		}
	}
	
	class AddButtonListener implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedOutfitId = ((PopupView) view).getSelectedOutfitToAdd();
			DatabaseResult databaseResult = outfitRepository.getOutfitById(selectedOutfitId);			
			Outfit selectedOutfit = (Outfit)databaseResult.getObject();
			
			if(selectedOutfit!=null)
			{
				addOutfit(selectedOutfit);	
			}
			else
			{
				//TODO message basılacak
			}	
		}
	}
	
	public void addOutfit(Outfit outfit) {
		((Collection)model).getOutfits().add(outfit);
		updateLists();
		outfitRepository.saveChanges();

	}

	public void removeOutfit(Outfit outfit) throws ItemNotFoundException {
		((Collection)model).getOutfits().remove(outfit);
		updateLists();
		outfitRepository.saveChanges();
	}
	
	private void updateLists()
	{
		UpdatedList[] updatedLists = new UpdatedList[2];
		updatedLists[0] = new UpdatedList(service.setOwnedOutfitsList((Collection)model));
		updatedLists[1] = new UpdatedList(service.setOtherOutfitsList((Collection)model));
		model.setAndNotify(updatedLists);
	}

}
