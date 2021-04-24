package app;

import factory.Creator;
import fileio.FileIO;
import fileio.IFileIO;
import model.Outfit;
import model.User;
import storage.IContainer;
import view.OutfitView;

public class app {

	public static void main(String[] args) throws Exception  {
		IFileIO fileIO = new FileIO(new Creator());
		IContainer<Outfit> outfits = fileIO.readOutfits("data//outfits.json");
	
		IContainer<User> users = fileIO.readUsers(outfits,"data//users.xml");
		OutfitView outfitView = new OutfitView("outfit", outfits.getById("38"), 500, 500);
	
	}
}

