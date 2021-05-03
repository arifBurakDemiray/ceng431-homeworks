package view;

import javax.swing.JWindow;

import controller.Consumable;
import controller.LoginController;
import model.Login;

public class AppWindow extends JWindow {

	protected static Frame FRAME;
	private static final long serialVersionUID = -8130256364738099887L;

	/**
	 * The constructor is the starter for the views. It initialise the login screen.
	 */
	public AppWindow() {
		FRAME = new Frame("Outfigram");
		Login lg = new Login(null);
		LoginView loginView = new LoginView(lg);
		final Consumable loginController = new LoginController(lg, loginView);
		loginController.supressNotUsed();
	}
}
