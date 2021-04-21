package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.swing.*;


public class app {

	String sFrameTitle = "SimpleTable Example";
	JFrame frame;
	JPanel paneContent; // To be composed of ScrollingTable & Buttons
	JScrollPane paneScrollingTable; // To be composed of a Table
	JTable tableMain;
	JPanel paneButtons;

	public void init() {
		// Create main frame
		frame = new JFrame(sFrameTitle);
		// Create content pane
		paneContent = new JPanel(new BorderLayout());
		JButton button = new JButton("asd");
		button.setBounds(10, 10, 10, 10);
		// Add panes to the content pane
		paneContent.add(button, BorderLayout.CENTER);
		JButton buttonx = new JButton("xxx");
		buttonx.setBounds(10, 10, 10, 10);

		paneContent.add(buttonx, BorderLayout.CENTER);
		// set the content pane for the main window
		// set the content pane for the main window
		frame.setContentPane(paneContent);

		// Set frame size
		frame.setSize(new Dimension(300, 155));
		// frame.pack();

		// Center the window
		CenterFrame(frame);
		// Show the window
		frame.setVisible(true);
	}

	void PopulateScrollingTablePane() {
		// First construct the data intended for the table
		String asColName[] = { "Power", "2^Power" };
		int aiExponent[] = { 1, 2, 5, 10, 20 };
		// Create matrix of Objects to populate table
		Object[][] aoData = new Object[aiExponent.length][asColName.length];
		// Then populate matrix intended for the table
		for (int iRow = 0; iRow < aiExponent.length; iRow++) {
			aoData[iRow][0] = Integer.toString(aiExponent[iRow]);
			int iVal = 1;
			for (int iCol = 0; iCol < aiExponent[iRow]; iCol++) {
				iVal *= 2;
			}
			aoData[iRow][1] = Integer.toString(iVal);
		}
		// Then create and populate the table
		tableMain = new JTable(aoData, asColName);
		// Finally, populate a Scrolling Pane with the table
		paneScrollingTable = new JScrollPane(tableMain);
	}

	void PopulateButtonPane() {
		paneButtons = new JPanel(new GridLayout());
		for (int i = 0; i < 3; i++) {
			JButton btn = new JButton(Integer.toString(i));
			paneButtons.add(btn);
		}
	}

	public void CenterFrame(JFrame jframeRef) {
		// Get a reference top the window toolkit
		Toolkit theKit = jframeRef.getToolkit();
		// Populate the Dimension object with the displayable area per the OS
		Dimension wndSize = theKit.getScreenSize();
		// Get frames size;
		Rectangle rc = jframeRef.getBounds();
		int x = (wndSize.width - rc.width) / 2;
		int y = (wndSize.height - rc.height) / 2;
		// Set the frame's position to screen center & size to half screen size
		jframeRef.setBounds(x, y, // Position
				rc.width, rc.height); // Size
		// Set the application to automatically close when the frame closes
		jframeRef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		//app st = new app();
		// Build &show the window
		//st.init();

		try {
			File inputFile = new File("data//users.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc  = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("user");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Username: " + eElement.getAttribute("userName"));
					System.out.println(
							"Followers : " + eElement.getElementsByTagName("followers").item(0).getTextContent());
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
