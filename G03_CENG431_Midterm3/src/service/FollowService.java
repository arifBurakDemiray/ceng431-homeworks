package service;

import javax.swing.DefaultListModel;
import storage.IContainer;

public class FollowService {

	public FollowService() {
	}

	public DefaultListModel<String> setFollowList(IContainer<String> followList) {

		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (String user : followList.getContainer()) {
			listModel.addElement(user);
		}
		return listModel;

	}
}
