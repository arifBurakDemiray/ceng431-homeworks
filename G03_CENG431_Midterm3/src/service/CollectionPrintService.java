package service;

import fileio.DatabaseResult;
import fileio.UserRepository;
import model.User;
import storage.IContainer;
import storage.StringContainer;

public class CollectionPrintService {

	private final UserRepository up;
	private final User user;
	
	public CollectionPrintService(User model) {
		this.user = model;
		this.up = new UserRepository();
	}
	
	
	public IContainer<String> getScroolString() {
		final IContainer<String> followings = user.getFollowings();
		IContainer<String> result = new StringContainer();
		for(String name: followings) {
			DatabaseResult dr = up.getUserByName(name);
			if(dr!=null) {
				final User temp = (User)dr.getObject();
				result.add("---"+temp.getUserName()+"---");
				result.add(temp.getCollections().toString());
			}
		}
		
		return result;
	}
}
