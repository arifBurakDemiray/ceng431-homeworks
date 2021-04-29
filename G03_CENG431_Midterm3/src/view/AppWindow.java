package view;

import javax.swing.JFrame;
import javax.swing.JWindow;

import controller.HomeController;
import controller.LoginController;
import configuration.ConfigurationManager;
import model.Login;

public class AppWindow extends JWindow{

	protected static View VIEW;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8130256364738099887L;
	
	public AppWindow() throws Exception {
		VIEW = new View();
		(new ConfigurationManager()).configureServices();
		Login lg = new Login(null);
		LoginView loginView = new LoginView(lg);
		VIEW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VIEW.setBounds(100, 100, 720, 450);
		VIEW.getContentPane().add(loginView);
		VIEW.setVisible(true);
		LoginController loginController = new LoginController(lg,loginView);
		HomeView userView = new HomeView(lg);
		VIEW.getContentPane().add(userView);
		HomeController hc = new HomeController(lg,userView);
	}
}
