package view.user;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Collection;
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
	protected JList<String> listOfNames;
	private JPanel contentPane;
	public JFrame popup;
	

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
		
		DefaultListModel<String> listModel = setList();
		listOfNames = new JList<String>(listModel);
		listOfNames.setBounds(150, 150, 200, 200);
		listOfNames.setVisible(true);
		contentPane.add(listOfNames);
		setVisible(true);

	}

	protected Observable model() {
		return model;
	}

	public void addBackButtonListener(ActionListener listener) {
		back.addActionListener(listener);
	}

	public void addSelectCollectionListener(MouseListener listener) {
		listOfNames.addMouseListener(listener);
	}

	public String getSelectedCollection() {

		String name = listOfNames.getSelectedValue();

		return name;
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
		}

	}

}
