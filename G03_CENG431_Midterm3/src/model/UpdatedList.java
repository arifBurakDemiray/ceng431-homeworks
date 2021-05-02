package model;

import javax.swing.DefaultListModel;

public class UpdatedList {

	private DefaultListModel<String> listModel; // holds the given list for scrollable panes

	/**
	 * This is a model for list of scrollable panes which is used update operations
	 * to update list of scrollable panes
	 * 
	 * @param listModel
	 */
	public UpdatedList(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

}
