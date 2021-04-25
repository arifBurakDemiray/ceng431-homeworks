package app;

import controller.LoginController;
import factory.Creator;
import fileio.FileIO;
import fileio.IFileIO;
import model.Outfit;
import model.User;
import storage.IContainer;
import view.LoginView;
import view.UserView;
import view.View;

public class app {

	public static void main(String[] args) throws Exception  {
		IFileIO fileIO = new FileIO(new Creator());
		IContainer<Outfit> outfits = fileIO.readOutfits("data//outfits.json");

	
		IContainer<User> users = fileIO.readUsers(outfits,"data//users.xml");
		Outfit outfit = outfits.getById("38");
		LoginView loginView = new LoginView(null);
		LoginController loginController = new LoginController(loginView,users);
		UserView userView = new UserView("outfit", users.getByName("furkan") , 1000, 1000);
		View view = new View(loginView,userView);
		//OutfitView outfitView = new OutfitView("outfit", outfit , 500, 500);
		//OutfitController outfitController = new OutfitController(outfit,outfitView);
		fileIO.writeOutfits(outfits, "data//denemeO.json");
		fileIO.writeUsers(users, "data//denemeU.xml");
		
	
	}
}

