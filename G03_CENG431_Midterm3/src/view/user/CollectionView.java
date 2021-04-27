package view.user;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import model.Collection;
import model.CollectionList;
import model.User;
import observation.Observable;
import observation.Observer;
import storage.IContainer;

public class CollectionView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2106315297457499256L;
	protected Observable model;
	private JButton back;
	private JButton createCollection;
	private JButton okey;
	private TextField collectionName;
	private JScrollPane scrollPaneOfListOfCollectionsNames;
	protected JList<String> listOfCollectionsNames;
	private JPanel contentPane;
	public JFrame popup;
	private JLabel myCollections;
	private JLabel infoMessage;

	public CollectionView(Observable model) {
		this.model = model;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		popup = new JFrame("Popup");
		popup.setBounds(200, 200, 200, 200);
		popup.setBackground(Color.BLACK);
		popup.setVisible(false);
		
		back = new JButton("Back");
		back.setBounds(23, 11, 89, 23);
		contentPane.add(back);
		
		createCollection = new JButton("Create Collection");
		createCollection.setBounds(260, 280, 100, 25);
		contentPane.add(createCollection);
		
		okey = new JButton("Create");
		okey.setBounds(260, 350, 100, 25);
		contentPane.add(okey);
		okey.setVisible(false);
		
		collectionName = new TextField();
		collectionName.setBounds(260, 310, 100, 30);
		contentPane.add(collectionName);
		collectionName.setVisible(false);
		
		myCollections = new JLabel("My Collections");
		myCollections.setBounds(200, 25, 150, 25);
		contentPane.add(myCollections);
		
		infoMessage = new JLabel("Select a collection clicking to add/remove outfits");
		infoMessage.setBounds(210, 250, 400, 30);
		contentPane.add(infoMessage);
		
		DefaultListModel<String> listModel = setList();
		listOfCollectionsNames = new JList<String>(listModel);
		listOfCollectionsNames.setVisible(true);
		
		scrollPaneOfListOfCollectionsNames = new JScrollPane(listOfCollectionsNames);
		scrollPaneOfListOfCollectionsNames.setBounds(200, 50, 200,200);
		contentPane.add(scrollPaneOfListOfCollectionsNames);
		

		setVisible(true);

	}

	protected Observable model() {
		return model;
	}

	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	public void addSelectCollectionListener(MouseListener listener) {
		listOfCollectionsNames.addMouseListener(listener);
	}
	
	public void addCreateCollectionButtonListener(ActionListener listener) {
		createCollection.addActionListener(listener);
	}
	
	public void addOkeyButtonListener(ActionListener listener) {
		okey.addActionListener(listener);
	}

	public String getSelectedCollection() {
		String name = listOfCollectionsNames.getSelectedValue();
		return name;
	}
	
	public String getCollectionName(){
		return collectionName.getText();
	}

	private DefaultListModel<String> setList() {
		IContainer<Collection> collectionsList = ((User) model).getCollections();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (Collection collection : collectionsList.getContainer()) {
			listModel.addElement(collection.getName());
		}
		return listModel;
	}

	@Override
	public void update(Observable observable, Object args) {

		if (args.equals("back")) {
			setVisible(false);

		} else if (args.equals("select")) {
			popup.setTitle(getSelectedCollection());
			popup.setVisible(true);
		}else if(args.equals("Okey")) {
			if(observable instanceof CollectionList)
				listOfCollectionsNames.setModel(((CollectionList) observable).getList());
			okey.setVisible(false);
			collectionName.setVisible(false);
			collectionName.setText("");
		} else if (args.equals("Create")) {
			okey.setVisible(true);
			collectionName.setVisible(true);
		}

	}

}
