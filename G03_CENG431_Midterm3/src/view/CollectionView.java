package view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import enums.ButtonState;
import model.UpdatedList;
import observation.Observable;
import observation.Observer;

/**
 * The collection view is a view which user sees his/her collections. And he/she
 * can create a new collection or open a collection by clicking to see, add,
 * remove outfit.
 *
 */
public class CollectionView extends JPanel implements Observer {

	private static final long serialVersionUID = -2106315297457499256L;
	protected Observable model;
	private JButton back; // back button
	private JButton createCollection; // create collection button
	private JButton okey; // okey button for create collection process
	private JTextField collectionName; // it holds name of collection
	private JScrollPane scrollPaneOfListOfCollectionsNames; // scroll pane which contains list of collections' names
	protected JList<String> listOfCollectionsNames; // JList of collections' names
	private JLabel myCollections; // Text header for user interface
	private JLabel infoMessage; // Text message for user interface

	public CollectionView(Observable model) {

		// set the observable and view elements in the view.
		this.model = model;
		AppWindow.FRAME.getContentPane().add(this);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		back = new JButton("Back");
		back.setBounds(23, 11, 89, 23);
		add(back);

		createCollection = new JButton("Create Collection");
		createCollection.setBounds(225, 250, 150, 25);
		add(createCollection);
		
		okey = new JButton("Create");
		okey.setBounds(225, 320, 150, 25);
		add(okey);
		okey.setVisible(false);

		collectionName = new JTextField();
		collectionName.setBounds(225, 280, 150, 30);
		add(collectionName);
		collectionName.setVisible(false);

		myCollections = new JLabel("My Collections");
		myCollections.setBounds(200, 10, 150, 25);
		add(myCollections);

		infoMessage = new JLabel("<html>Select a collection clicking on name twice to add/remove outfits."
				+ "To create a new collection, click the 'Create Collection' button and type a collection name.</html>");
		infoMessage.setBounds(450, 70, 200, 200);
		add(infoMessage);

		// CollectionController initialises list using update at the start
		// and in the continuation. Default it is empty.
		listOfCollectionsNames = new JList<String>();
		listOfCollectionsNames.setVisible(true);

		scrollPaneOfListOfCollectionsNames = new JScrollPane(listOfCollectionsNames);
		scrollPaneOfListOfCollectionsNames.setBounds(200, 35, 200, 200);
		add(scrollPaneOfListOfCollectionsNames);

		setVisible(true);

	}

	/**
	 * The function returns observable of view
	 * 
	 * @return observable
	 */
	protected Observable model() {
		return model;
	}

	/**
	 * The function helps for detecting of clicking back button using listener
	 * 
	 * @param listener
	 */
	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	/**
	 * The function helps for selecting of a collection from collection list using
	 * listener.
	 * 
	 * @param listener
	 */
	public void addSelectCollectionListener(MouseListener listener) {
		listOfCollectionsNames.addMouseListener(listener);
	}

	/**
	 * The function helps for detecting of clicking a new collection creator button
	 * using listener.
	 * 
	 * @param listener
	 */
	public void addCreateCollectionButtonListener(ActionListener listener) {
		createCollection.addActionListener(listener);
	}

	/**
	 * The function helps for detecting of clicking okey button using listener.
	 * 
	 * @param listener
	 */
	public void addOkeyButtonListener(ActionListener listener) {
		okey.addActionListener(listener);
	}

	/**
	 * The function returns the selected collection name of the collection list.
	 * 
	 * @param String of selected collection name
	 */
	public String getSelectedCollection() {
		String name = listOfCollectionsNames.getSelectedValue();
		return name;
	}

	/**
	 * The function returns the clicked collection name
	 * 
	 * @return String of clicked collection name
	 */
	public String getCollectionName() {
		return collectionName.getText();
	}

	@Override
	public void update(Observable observable, Object args) {

		// If back button is clicked, remove the view from frame.
		if (args instanceof ButtonState && args == ButtonState.BACK_BUTTON) {
			AppWindow.FRAME.getContentPane().remove(this);
		}

		// If CollectionContainer invokes modifyAndSet() and it notifies with a
		// UpdatedList model,
		// we understand that there is a change in the collection name list
		// and update the JList with gotten list from UpdatedList model
		// Or we understand that list is initialised at the start.
		// for example after creating a new collection, it updates the list.
		else if (args instanceof UpdatedList) {
			listOfCollectionsNames.setModel(((UpdatedList) args).getListModel());
			okey.setVisible(false);
			collectionName.setVisible(false);
			collectionName.setText("");
		}

		// If create a collection button is clicked, show okey button in the screen
		else if (args instanceof ButtonState && args == ButtonState.CREATE_BUTTON) {
			okey.setVisible(true);
			collectionName.setVisible(true);
		}

	}

}
