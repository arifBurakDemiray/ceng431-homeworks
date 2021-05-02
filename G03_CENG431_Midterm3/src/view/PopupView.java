package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import enums.ButtonState;
import model.UpdatedList;
import observation.Observable;
import observation.Observer;

/**
 * The pop up view is a view which shows the selected collection's outfits. And
 * user can add or remove outfit.
 *
 */
public class PopupView extends JFrame implements Observer {

	private static final long serialVersionUID = 3365974101630467096L;
	private Observable model;
	private JPanel contentPane;

	// scroll pane which contains list of added outfits of// collection
	private JScrollPane scrollPaneOfListOfOwnedOutfitsNames;
	// JList of added outfits of collection
	private JList<String> listOfOwnedOutfitsNames;
	// scroll pane which contains list of other outfits which can be added to the
	// collection
	private JScrollPane scrollPaneOflistOfOtherOutfitsNames;
	// JList of other outfits which can be added to the collection
	private JList<String> listOfOtherOutfitsNames;
	private JButton removeButton; // remove selected outfit button
	private JButton addButton; // add selected outfit button
	private JLabel myOutfits; // text
	private JLabel otherOutfits; // text
	private JLabel infoMessage; // Text message for user interface

	public PopupView(Observable model) {
		// set the observable and view elements in the view.
		this.model = model;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 100, 450, 450);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		removeButton = new JButton("Remove");
		removeButton.setBounds(100, 200, 100, 25);
		contentPane.add(removeButton);

		addButton = new JButton("Add");
		addButton.setBounds(300, 200, 100, 25);
		contentPane.add(addButton);

		myOutfits = new JLabel("Outfits in The Collection");
		myOutfits.setBounds(50, 25, 150, 25);
		contentPane.add(myOutfits);

		otherOutfits = new JLabel("Outfits in Store");
		otherOutfits.setBounds(250, 25, 150, 25);
		contentPane.add(otherOutfits);

		// PopupController initialises list using update at the start
		// and in the continuation. Default it is empty.
		listOfOwnedOutfitsNames = new JList<String>();
		listOfOwnedOutfitsNames.setVisible(true);

		// PopupController initialises list using update at the start
		// and in the continuation. Default it is empty.
		listOfOtherOutfitsNames = new JList<String>();
		listOfOtherOutfitsNames.setVisible(true);

		scrollPaneOfListOfOwnedOutfitsNames = new JScrollPane(listOfOwnedOutfitsNames);
		scrollPaneOfListOfOwnedOutfitsNames.setBounds(50, 50, 150, 150);
		contentPane.add(scrollPaneOfListOfOwnedOutfitsNames);

		scrollPaneOflistOfOtherOutfitsNames = new JScrollPane(listOfOtherOutfitsNames);
		scrollPaneOflistOfOtherOutfitsNames.setBounds(250, 50, 150, 150);
		contentPane.add(scrollPaneOflistOfOtherOutfitsNames);
		
		infoMessage = new JLabel("<html>Select an outfit by clicking on name once. After that click add/remove button."
				+ "<br/> To view outfit, click on name twice.</html>");
		infoMessage.setBounds(150, 220, 150, 200);
		add(infoMessage);

		setVisible(true);

	}

	/**
	 * The function returns the observable of view
	 * 
	 * @return model
	 */
	protected Observable model() {
		return model;
	}

	/**
	 * The function helps for detecting of clicking remove button using listener
	 * 
	 * @param listener
	 */
	public void addRemoveButtonListener(ActionListener removeSelectedOutfitListener) {
		removeButton.addActionListener(removeSelectedOutfitListener);
	}

	/**
	 * The function helps for detecting of clicking add button using listener
	 * 
	 * @param listener
	 */
	public void addAddButtonListener(ActionListener addSelectedOutfitListener) {
		addButton.addActionListener(addSelectedOutfitListener);
	}
	
	/**
	 * The function helps for detecting of selecting a outfit from JList to open pop op of outfit pop up view
	 * 
	 * @param listener
	 */
	public void addSelectOutfitListener(MouseListener listener) {
		listOfOtherOutfitsNames.addMouseListener(listener);
		listOfOwnedOutfitsNames.addMouseListener(listener);
	}


	/**
	 * The function returns the selected outfit of owned outfits list which is
	 * wanted to be removed.
	 * 
	 * @param String name of selected outfit of owned outfits list.
	 */
	public String getSelectedOutfitToRemove() {
		String name = listOfOwnedOutfitsNames.getSelectedValue();
		return name;
	}

	/**
	 * The function returns the selected outfit of other outfits list which is
	 * wanted to be added.
	 * 
	 * @param String name of selected outfit of other outfits list.
	 */
	public String getSelectedOutfitToAdd() {
		String name = listOfOtherOutfitsNames.getSelectedValue();
		return name;
	}

	@Override
	public void update(Observable observable, Object args) {

		// If the back button is clicked, set the pop up view invisible
		if (args instanceof ButtonState && args == ButtonState.BACK_BUTTON) {
			setVisible(false);

		}

		// If PopupController invokes modifyAndSet() and it notifies with a
		// UpdatedList model,
		// we understand that there is a change in lists
		// Or we understand that lists are initialised at the start.
		// for example after adding a new outfit, it adds the selected outfit to the
		// owned list and it updates the list.
		// or after removing a outfit, it removes the selected outfit from the
		// owned list and it updates the list.
		if (args instanceof UpdatedList[]) {
			UpdatedList[] temp = (UpdatedList[]) args;
			listOfOwnedOutfitsNames.setModel(((UpdatedList) temp[0]).getListModel());
			listOfOtherOutfitsNames.setModel(((UpdatedList) temp[1]).getListModel());

		}

	}

}
