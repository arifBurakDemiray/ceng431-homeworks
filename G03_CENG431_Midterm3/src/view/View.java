package view;

import javax.swing.JFrame;

import observation.Observable;
import observation.Observer;

public class View extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8368745851825149744L;

	
	public View(LoginView loginPage, UserView userPage){
		Observable model = loginPage.getModel();
		while(model==null){
			model=loginPage.getModel();
		}
		System.out.println("aaa");
		userPage.setVisible(true);
	}
	
	@Override
	public void update(Observable observable, Object args) {
		// TODO Auto-generated method stub
		
	}

}
