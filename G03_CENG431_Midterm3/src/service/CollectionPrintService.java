package service;

import javax.swing.DefaultListModel;

import fileio.DatabaseResult;
import fileio.UserRepository;
import model.Collection;
import model.Outfit;
import model.User;
import storage.IContainer;

public class CollectionPrintService {

	private final UserRepository up;
	private final User user;
	
	public CollectionPrintService(User model) {
		this.user = model;
		this.up = new UserRepository();
	}
	
	
	public DefaultListModel<String> getScroolString() {
		final IContainer<String> followings = user.getFollowings();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(String name: followings) {
			DatabaseResult dr = up.getUserByName(name);
			if(dr!=null) {
				final User temp = (User)dr.getObject();
				listModel.addElement("@"+temp.getUserName());
				for(Collection p: temp.getCollections()) {
					listModel.addElement("_"+p.getName());
					for(Outfit o: p.getOutfits()) {
						listModel.addElement("  |"+o.getType()+"-"+o.getId());
					}
				}
			}
		}
		
		return listModel;
	}
}
