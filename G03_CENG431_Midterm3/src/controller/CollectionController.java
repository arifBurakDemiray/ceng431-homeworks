package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import enums.ButtonState;
import model.Collection;
import model.CollectionReview;
import model.UpdatedList;
import model.User;
import observation.Observable;
import observation.Observer;
import service.CollectionService;
import view.CollectionView;
import view.PopupView;

/**
 * 
 * This class processes requests coming from CollectionView
 *
 */
public class CollectionController extends Consumable {

	private User model; // Model which is user for CollectionView
	private Observer view; // CollectionView
	private CollectionService service;

	public CollectionController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view); // view to user's observers
		service = new CollectionService();
		((CollectionView) this.view).addBackButtonListener(new BackButtonListener()); // add listeners to view
		((CollectionView) this.view).addSelectCollectionListener(new SelectCollectionListener());
		((CollectionView) this.view).addCreateCollectionButtonListener(new CreateCollectionButtonListener());
		((CollectionView) this.view).addOkeyButtonListener(new OkeyButtonListener());
		updateList(); // to show full list, update list of view
	}

	// This class handles requests that are coming from Back button in Collection
	// view
	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify(ButtonState.BACK_BUTTON); // say observers that back button pressed
			model.removeObserver(view); // and remove view because we are done with this view
		}
	}

	// This class handles requests that are coming from JList in Collection view
	// Its takes a selected text, if user has that kind of collection new screen
	// shows up
	class SelectCollectionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {// means user should click 2 times or above to show collection details
				String selectedCollectionName = ((CollectionView) view).getSelectedCollection();
				// get selected collection from user's collection
				Collection collection = service.getUserSelectedCollection(selectedCollectionName, model);
				// if it is found, show collection details
				if (collection != null) {
					CollectionReview collectionReview = new CollectionReview(collection,model);
					PopupView popupView = new PopupView(collectionReview); // create view from found collection
					final Consumable popupController = new PopupController(collectionReview, popupView);
					popupController.supressNotUsed(); // to consume warnings
				}
			}
		}

		// We only needed click function
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

	// If user clicks create button, we should show create text and okey button
	class CreateCollectionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// notify pop up view to show text and button
			model.setAndNotify(ButtonState.CREATE_BUTTON);
		}

	}

	// if user clicks okey button, create collection and add it to user
	class OkeyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String collectionName = ((CollectionView) view).getCollectionName();
			service.createCollection(collectionName, model); // create collection
			updateList(); // and update list
		}
	}

	// to remove observer from model
	class WindowListener extends WindowAdapter {
		Observer viewAdapter;
		Observable modelAdapter;

		public WindowListener(Observable collection, Observer pv) {
			super();
			this.viewAdapter = pv;
			this.modelAdapter = collection;

		}

		@Override
		// if window closed remove observer from model
		public void windowClosed(WindowEvent arg0) {
			modelAdapter.removeObserver(viewAdapter);
		}

	}

	// if user created a collection, update list to show new list
	private void updateList() {
		UpdatedList updatedList = new UpdatedList(service.setCollectionList(model));
		model.setAndNotify(updatedList);
	}

}
