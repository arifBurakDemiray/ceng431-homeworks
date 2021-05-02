package view;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private static final long serialVersionUID = -8368745851825149744L;

	/**
	 * It is the constructor of main frame
	 * @param name
	 */
	public Frame(String name){
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 450);
	}
	

}
