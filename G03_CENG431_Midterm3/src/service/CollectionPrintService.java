package service;

import javax.swing.DefaultListModel;

import fileio.DatabaseResult;
import fileio.UserRepository;
import model.Collection;
import model.Outfit;
import model.User;
import storage.IContainer;

/**
 * This class handles requests that coming from controller
 */
public class CollectionPrintService {

	private final UserRepository up;
	private final User user;

	public CollectionPrintService(User model) {
		this.user = model;
		this.up = new UserRepository();
	}

	/**
	 * This function renders scroll string of the home screen
	 * 
	 * @return rendered list
	 */
	public DefaultListModel<String> getScroolString() {
		final IContainer<String> followings = user.getFollowings();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (String name : followings) {
			DatabaseResult dr = up.getUserByName(name); // get user name
			if (dr.getObject() != null) { // if user found
				final User user = (User) dr.getObject(); // add user info
				listModel.addElement("User: " + user.getUserName());
				for (Collection collection : user.getCollections()) {// add followed user's collection
					listModel.addElement("    Collection: " + collection.getName());
					for (Outfit outfit : collection.getOutfits()) { // add collection's outfit
						listModel.addElement("        Outfit: " + outfit.getType() + "-" + outfit.getId());
					}
				}
			}
		}

		return listModel;
	}
}
