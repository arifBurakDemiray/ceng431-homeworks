package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.OutfitRepository;
import fileio.UserRepository;
import model.Collection;
import model.CollectionList;
import model.Outfit;
import model.User;
import observation.Observable;
import observation.Observer;
import storage.IContainer;
import view.user.CollectionView;
import view.user.PopupView;

public class CollectionController {

	private User model;
	private Observer view;

	public CollectionController(Observable model, Observer view) {
		this.model = (User) model;
		this.view = view;
		model.addObserver(view);
		((CollectionView) this.view).addBackButtonListener(new BackButtonListener());
		((CollectionView) this.view).addSelectCollectionListener(new SelectCollectionListener());
		((CollectionView) this.view).addCreateCollectionButtonListener(new CreateCollectionButtonListener());
		((CollectionView) this.view).addOkeyButtonListener(new OkeyButtonListener());
	}

	class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify("back");
			model.removeObserver(view);
		}
	}

	class SelectCollectionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			String selectedCollectionName = ((CollectionView) view).getSelectedCollection();
			Collection collection = null;
			try {
				collection = model.getCollections().getByName(selectedCollectionName);				
				PopupView pv = new PopupView(collection);
				PopupController pc = new PopupController(collection,pv);
			} catch (ItemNotFoundException | NotSupportedException e1) {
			}
			
			// model.setAndNotify("select");
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

	class CreateCollectionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAndNotify("Create");
			
		}
		
	}
	
	class OkeyButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String collectionName = ((CollectionView)view).getCollectionName();
			if( !collectionName.equals("") && collectionName!=null) {
				Collection collection = new Collection(collectionName);
				((User)model).getCollections().add(collection);
				(new UserRepository()).saveChanges();
				CollectionList newList = new CollectionList(setCollectionList());
				newList.addObserver(view);
				newList.setAndNotify("Okey");}
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
	
	private DefaultListModel<String> setCollectionList() {
		final IContainer<Collection> collections = ((User) model).getCollections();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Collection collection : collections) {
			listModel.addElement(collection.getName());

		}
		return listModel;
	}

}
