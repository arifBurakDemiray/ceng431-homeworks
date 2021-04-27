package model;

import javax.swing.DefaultListModel;

import observation.Observable;

public class CollectionList extends Observable{

	private DefaultListModel<String> list;
	
	public CollectionList(DefaultListModel<String> list) {
		this.list = list;
	}
	
	public DefaultListModel<String> getList(){
		return this.list;
	}
}
