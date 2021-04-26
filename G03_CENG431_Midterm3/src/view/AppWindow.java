package view;

import javax.swing.JWindow;

import controller.HomeController;
import controller.LoginController;
import factory.Creator;
import fileio.FileController;
import fileio.FileIO;
import fileio.IFileIO;
import model.Login;
import model.Outfit;
import model.User;
import storage.IContainer;

public class AppWindow extends JWindow{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8130256364738099887L;
	
	public AppWindow() throws Exception {
		FileController fc = new FileController(new Creator());
		fc.readAll();
		//Outfit outfit = outfits.getById("38");
		Login lg = new Login(null);
		LoginView loginView = new LoginView(lg);
		LoginController loginController = new LoginController(lg,loginView,fc.users());
		HomeView userView = new HomeView(lg);
		HomeController hc = new HomeController(lg,userView);
	}
}
