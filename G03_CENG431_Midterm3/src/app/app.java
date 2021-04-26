package app;

import controller.HomeController;
import controller.LoginController;
import factory.Creator;
import fileio.FileIO;
import fileio.IFileIO;
import model.Login;
import model.Outfit;
import model.User;
import storage.IContainer;
import view.HomeView;
import view.LoginView;

public class app {

	public static void main(String[] args) throws Exception  {
		IFileIO fileIO = new FileIO(new Creator());
		IContainer<Outfit> outfits = fileIO.readOutfits("data//outfits.json");

	
		IContainer<User> users = fileIO.readUsers(outfits,"data//users.xml");
		//Outfit outfit = outfits.getById("38");
		Login lg = new Login(null);
		LoginView loginView = new LoginView(lg);
		LoginController loginController = new LoginController(lg,loginView,users);
		HomeView userView = new HomeView(lg);
		HomeController hc = new HomeController(lg,userView);
		//OutfitView outfitView = new OutfitView("outfit", outfit , 500, 500);
		//OutfitController outfitController = new OutfitController(outfit,outfitView);
		fileIO.writeOutfits(outfits, "data//denemeO.json");
		fileIO.writeUsers(users, "data//denemeU.xml");
		
	
	}
}

