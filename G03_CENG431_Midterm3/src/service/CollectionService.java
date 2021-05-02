package service;

import javax.swing.DefaultListModel;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.UserRepository;
import model.Collection;
import model.User;
import storage.IContainer;

/**
 * This class processes requests coming from Collection controller
 */
public class CollectionService {

	/**
	 * Constructor
	 */
	public CollectionService() {
	}

	/**
	 * This function takes a user model and responses with user's collections names'
	 * 
	 * @param model which is a user
	 * @return list model for the CollectionView's JList which is collection names
	 *         of a user'
	 */
	public DefaultListModel<String> setCollectionList(User model) {

		IContainer<Collection> collectionsList = ((User) model).getCollections(); // get user collections
		DefaultListModel<String> listModel = new DefaultListModel<>(); // list model for the JList
		for (Collection collection : collectionsList) { // for each in user's collections
			listModel.addElement(collection.getName()); // take their names
		}
		return listModel; // and return list for the collections names
	}

	/**
	 * This function gets user's selection by given collection name if not exists
	 * returns null
	 * 
	 * @param collectionName name of the wanted collection
	 * @param user           that is wants selected collection
	 * @return collection if not found returns null
	 */
	public Collection getUserSelectedCollection(String collectionName, User user) {
		Collection collection = null;
		try {
			// get selected collection
			collection = user.getCollections().getByName(collectionName);
		} catch (ItemNotFoundException | NotSupportedException e) {
		}
		return collection;
	}

	/**
	 * This function creates new collection by given name and adds it to the user's
	 * collection
	 * 
	 * @param collectionName is going to be created collection
	 * @param user           that is wanted to create new collection
	 */
	public void createCollection(String collectionName, User model) {
		if (!collectionName.equals("") && collectionName != null) {// if not null and not empty
			Collection collection = new Collection(collectionName);// create collection
			((User) model).getCollections().add(collection);// add collection to user's
			(new UserRepository()).saveChanges();// save changes
		}
	}
}
