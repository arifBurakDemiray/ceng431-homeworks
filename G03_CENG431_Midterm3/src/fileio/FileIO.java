package fileio;

import factory.ICreatorService;
import fileio.parser.Parser;
import model.Outfit;
import model.User;
import storage.IContainer;
import storage.OutfitContainer;
import storage.UserContainer;

public class FileIO implements IFileIO {
	private FileRead fRead;
	private ICreatorService creator;
	private FileWrite fWrite;
	private Parser parser;

	public FileIO(ICreatorService creator) {
		this.fRead = new FileRead(); // initialise file read
		this.creator = creator;
		this.fWrite = new FileWrite(); // initialise file write
		this.parser = new Parser();
	}

	@Override
	public IContainer<Outfit> readOutfits(String filePath) throws Exception {
		IContainer<Outfit> outfits = null;

		String fileAll = fRead.readFile(filePath);// read file
		if (!fileAll.isBlank())// if not blank
			outfits = parser.parseOutfits(fileAll, this.creator); // parse products
		else
			outfits = new OutfitContainer(); // init empty repo

		return outfits;
	}

	@Override
	public IContainer<User> readUsers(IContainer<Outfit> outfits, String filePath) throws Exception {
		IContainer<User> users = null;

		String fileAll = fRead.readFile(filePath); // read file
		if (!fileAll.isBlank()) // if not blank
			users = parser.parseUsers(fileAll, this.creator); // parse users
		else
			users = new UserContainer(); // init empty repo

		return users;
	}

	@Override
	public void writeUsers(IContainer<User> users, String filePath) throws Exception {
		fWrite.writeItems(users, filePath);
	}

	@Override
	public void writeOutfits(IContainer<Outfit> outfits, String filePath) throws Exception {
		fWrite.writeItems(outfits, filePath);

	}

	

}
