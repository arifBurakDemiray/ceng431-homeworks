package service;

import javax.swing.DefaultListModel;
import model.Collection;
import model.User;
import storage.IContainer;

public class CollectionService {

	public CollectionService() {
	}

	public DefaultListModel<String> setCollectionList(User model) {

		IContainer<Collection> collectionsList = ((User) model).getCollections();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Collection collection : collectionsList.getContainer()) {
			listModel.addElement(collection.getName());
		}
		return listModel;

	}
}
