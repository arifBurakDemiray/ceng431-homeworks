package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JList;
import enums.ButtonState;
import model.CollectionReview;
import model.Outfit;
import model.OutfitReview;
import model.UpdatedList;
import observation.Observable;
import observation.Observer;
import service.PopupService;
import view.OutfitPopupView;
import view.PopupView;

/**
 * This class handles collection's outfit view, which is pop up view
 */
public class PopupController extends Consumable {

	private Observable model;
	private Observer view;
	private PopupService service;

	public PopupController(Observable model, Observer view) {
		this.model = model;
		this.view = view;
		model.addObserver(view);
		service = new PopupService();
		((PopupView) this.view).addRemoveButtonListener(new RemoveButtonListener());
		((PopupView) this.view).addAddButtonListener(new AddButtonListener());
		((PopupView) this.view).addSelectOutfitListener(new SelectOutfitListener());
		updateLists(); // initialise lists of in and out collection outfits
	}

	// by this class user can select one of the outfits
	class SelectOutfitListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				Component component = e.getComponent();
				Outfit outfit = null;
				@SuppressWarnings("unchecked")
			
				String selectedItemName = ((JList<String>) component).getSelectedValue();
				if(selectedItemName != null && !selectedItemName.equals(""))
				{
					String outfitId = selectedItemName.split(":")[0];
					outfit = service.getOutfitById(outfitId);
				}
			
				if (outfit != null) // if outfit is found
				{ // create outfit review model
					Observable outfitReview = new OutfitReview(outfit, ((CollectionReview) model).getUser());
					Observer outfitPopupView = new OutfitPopupView(outfitReview);
					final Consumable outfitPopupController = new OutfitPopupController(outfitReview, outfitPopupView);
					outfitPopupController.supressNotUsed();
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	// this class handles back button events
	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify(ButtonState.BACK_BUTTON);
			model.removeObserver(view);
		}
	}

	// this class handles remove button events
	class RemoveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedOutfitDetail = ((PopupView) view).getSelectedOutfitToRemove();
			service.removeOutfitFromCollection(selectedOutfitDetail, ((CollectionReview) model));
			updateLists();
		}
	}

	// this class handles add button events
	class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedOutfitDetail = ((PopupView) view).getSelectedOutfitToAdd();
			service.addOutfitToCollection(selectedOutfitDetail, ((CollectionReview) model));
			updateLists();
		}
	}

	// this function updates list of in and out outfits from collection
	private void updateLists() {
		UpdatedList[] updatedLists = new UpdatedList[2];
		updatedLists[0] = new UpdatedList(service.setOwnedOutfitsList(((CollectionReview) model)));
		updatedLists[1] = new UpdatedList(service.setOtherOutfitsList(((CollectionReview) model)));
		model.setAndNotify(updatedLists);
	}

}
