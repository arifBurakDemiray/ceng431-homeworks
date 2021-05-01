package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import enums.ButtonState;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.UserRepository;
import model.Collection;
import model.UpdatedList;
import model.User;
import observation.Observable;
import observation.Observer;
import service.CollectionService;
import view.CollectionView;
import view.PopupView;

public class CollectionController {

	private User model;
	private Observer view;
	private CollectionService service;
	public CollectionController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		service = new CollectionService();
		((CollectionView) this.view).addBackButtonListener(new BackButtonListener());
		((CollectionView) this.view).addSelectCollectionListener(new SelectCollectionListener());
		((CollectionView) this.view).addCreateCollectionButtonListener(new CreateCollectionButtonListener());
		((CollectionView) this.view).addOkeyButtonListener(new OkeyButtonListener());
		updateList();
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify(ButtonState.BACK_BUTTON);
			model.removeObserver(view);
		}
	}

	class SelectCollectionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				String selectedCollectionName = ((CollectionView) view).getSelectedCollection();
				Collection collection = null;
				try {
					collection = model.getCollections().getByName(selectedCollectionName);
					PopupView pv = new PopupView(collection);
					PopupController pc = new PopupController(collection, pv);
				} catch (ItemNotFoundException | NotSupportedException e1) {
				}

			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class CreateCollectionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify(ButtonState.CREATE_BUTTON);

		}

	}

	class OkeyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String collectionName = ((CollectionView) view).getCollectionName();
			if (!collectionName.equals("") && collectionName != null) {
				Collection collection = new Collection(collectionName);
				((User) model).getCollections().add(collection);
				(new UserRepository()).saveChanges();
				updateList();
			}
		}

	}

	class WindowListener extends WindowAdapter {
		Observer viewAdapter;
		Observable modelAdapter;

		public WindowListener(Observable collection, Observer pv) {
			super();
			this.viewAdapter = pv;
			this.modelAdapter = collection;

		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			modelAdapter.removeObserver(viewAdapter);
		}

	}

	

	private void updateList()
	{	
		UpdatedList updatedList = new UpdatedList(service.setCollectionList(model));
		model.setAndNotify(updatedList);
	}

}
