package view.user;

import java.awt.event.ActionListener;

import java.awt.event.MouseMotionListener;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import fileio.FileController;
import model.Collection;
import model.Outfit;
import observation.Observable;
import observation.Observer;
import storage.IContainer;

public class PopupView extends JFrame implements Observer {


	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3365974101630467096L;
	private Observable model;
	private JPanel contentPane;
	protected JList<String> listOfOwnedOutfitsNames;
	protected JButton removeButton;
	protected JList<String> listOfOtherOutfitsNames;
	protected JButton addButton;

	public PopupView(Observable model){
		this.model = model;
		setBounds(200, 200, 520, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		removeButton = new JButton("Remove");
		removeButton.setBounds(261, 350, 89, 23);
		contentPane.add(removeButton);

		
		addButton = new JButton("Add");
		addButton.setBounds(561, 350, 89, 23);
		contentPane.add(addButton);
		
		DefaultListModel<String> ownedOutfitsListModel = setOwnedOutfitsList();
		listOfOwnedOutfitsNames = new JList<String>(ownedOutfitsListModel);

		listOfOwnedOutfitsNames.setBounds(0, 150, 200, 200);
		listOfOwnedOutfitsNames.setVisible(true);
		contentPane.add(listOfOwnedOutfitsNames);
		
		DefaultListModel<String> otherOutfitsListModel = setOtherOutfitsList();
		listOfOtherOutfitsNames = new JList<String>(otherOutfitsListModel);

		listOfOtherOutfitsNames.setBounds(550, 150, 200, 200);
		listOfOtherOutfitsNames.setVisible(true);
		contentPane.add(listOfOtherOutfitsNames);
		
		setVisible(true);
		
	
	}
	
	protected Observable model() {
		return model;
	}
	
	public void addWindowExitListener(MouseMotionListener windowCloser){
		addMouseMotionListener(windowCloser);
	}
	
	public void addRemoveButtonListener(ActionListener removeSelectedOutfitListener) {
		removeButton.addActionListener(removeSelectedOutfitListener);
	}
	
	public void addAddButtonListener(ActionListener addSelectedOutfitListener) {
		addButton.addActionListener(addSelectedOutfitListener);
	}

	
	
	public String getSelectedOutfitToRemove() {
		String name = listOfOwnedOutfitsNames.getSelectedValue();
		return name;
	}
	
	public String getSelectedOutfitToAdd() {
		String name = listOfOtherOutfitsNames.getSelectedValue();
		return name;
	}

	private DefaultListModel<String> setOwnedOutfitsList() {
		IContainer<Outfit> outfits = ((Collection)model).getOutfits();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Outfit outfit : outfits.getContainer()) {
			listModel.addElement(outfit.getId());
		}
		return listModel;
	}
	
	private DefaultListModel<String> setOtherOutfitsList() {
		IContainer<Outfit> outfits = FileController.outfits();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Outfit outfit : outfits.getContainer()) {
			try {
				((Collection)model).getOutfits().getById(outfit.getId());
			} catch (ItemNotFoundException | NotSupportedException e) {
				listModel.addElement(outfit.getId());
			}
			
		}
		return listModel;
	}

	@Override
	public void update(Observable observable, Object args) {

		if (args.equals("back")) {
			setVisible(false);

		} else if (args.equals("select")) {
			
		}
		else if(args.equals("Close")){
			observable.removeObserver(this);
			dispose();
		}
		
		else if(args.equals("updateList")){
			listOfOwnedOutfitsNames.setModel(setOwnedOutfitsList());
			listOfOtherOutfitsNames.setModel(setOtherOutfitsList());
		}


	}

}
