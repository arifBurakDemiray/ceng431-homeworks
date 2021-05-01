package view;

import java.awt.event.ActionListener;
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

public class PopupView extends JFrame implements Observer {

	private static final long serialVersionUID = 3365974101630467096L;
	private Observable model;
	private JPanel contentPane;
	private JScrollPane scrollPaneOfListOfOwnedOutfitsNames;
	private JScrollPane scrollPaneOflistOfOtherOutfitsNames;
	private JList<String> listOfOwnedOutfitsNames;
	private JButton removeButton;
	private JLabel myOutfits;
	private JLabel otherOutfits;
	private JList<String> listOfOtherOutfitsNames;
	private JButton addButton;

	public PopupView(Observable model) {
		
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


		listOfOwnedOutfitsNames = new JList<String>();
		listOfOwnedOutfitsNames.setVisible(true);

		listOfOtherOutfitsNames = new JList<String>();
		listOfOtherOutfitsNames.setVisible(true);

		scrollPaneOfListOfOwnedOutfitsNames = new JScrollPane(listOfOwnedOutfitsNames);
		scrollPaneOfListOfOwnedOutfitsNames.setBounds(50, 50, 150, 150);
		contentPane.add(scrollPaneOfListOfOwnedOutfitsNames);

		scrollPaneOflistOfOtherOutfitsNames = new JScrollPane(listOfOtherOutfitsNames);
		scrollPaneOflistOfOtherOutfitsNames.setBounds(250, 50, 150, 150);
		contentPane.add(scrollPaneOflistOfOtherOutfitsNames);

		setVisible(true);

	}

	protected Observable model() {
		return model;
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

	@Override
	public void update(Observable observable, Object args) {

		if (args instanceof ButtonState && args==ButtonState.BACK_BUTTON) {
			setVisible(false);

		} 
		
		if (args instanceof UpdatedList[]) {
			UpdatedList[] temp = (UpdatedList[])args;
			listOfOwnedOutfitsNames.setModel(((UpdatedList)temp[0]).getListModel());
			listOfOtherOutfitsNames.setModel(((UpdatedList)temp[1]).getListModel());

		} 

	}

}
