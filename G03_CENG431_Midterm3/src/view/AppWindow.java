package view;

import javax.swing.JWindow;

import controller.HomeController;
import controller.LoginController;
import model.Login;

public class AppWindow extends JWindow{

	protected static Frame FRAME;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8130256364738099887L;
	
	public AppWindow() throws Exception {
		FRAME = new Frame("Outfigram");
		Login lg = new Login(null);		
		LoginView loginView = new LoginView(lg);
		final var loginController = new LoginController(lg,loginView);
		
		
		
	}
}
