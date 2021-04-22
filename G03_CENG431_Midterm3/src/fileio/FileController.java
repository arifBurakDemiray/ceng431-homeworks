package fileio;

import factory.ICreatorService;
import model.Outfit;
import model.User;
import storage.IContainer;

import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;

/**
 * This class holds all informations
 */
public class FileController {

	private IContainer<User> users;
	private IContainer<Outfit> outfits;
	private IFileIO fileIO;

	public FileController(ICreatorService creator) {
		this.fileIO = new FileIO(creator);
	}

	/**
	 * This function reads all entities
	 * 
	 * @throws FileFormatException
	 */
	public void readAll() throws FileFormatException {
		try {
			outfits = fileIO.readOutfits("data\\products.json");
			users = fileIO.readUsers(outfits,"data\\users.json");
		} catch (Exception e) {
			throw new FileFormatException(e.getMessage());
		}
	}

	/**
	 * This function writes all entities
	 * 
	 * @throws FileFormatException
	 */
	public void writeAll() throws FileFormatException {
		try {
			fileIO.writeUsers(users, "data\\users.json");
			fileIO.writeOutfits(outfits, "data\\products.json");
		} catch (Exception e) {
			throw new FileFormatException(e.getMessage());
		}
	}
	
	
	// Add user to system
	public boolean addUser(User user) {
		return this.users.add(user);
	}

	// add product to system
	public boolean addOutfit(Outfit outfit) {
		return this.outfits.add(outfit);
	}

	// get by user name
	public User getByUserName(String userName) throws ItemNotFoundException, NotSupportedException {
		return this.users.getByName(userName);
	}

	// get by product id
	public Outfit getByOutfitId(String outfitId) throws ItemNotFoundException, NotSupportedException {
		return this.outfits.getById(outfitId);
	}

	public IContainer<User> users() {
		return this.users;
	}

	public IContainer<Outfit> products() {
		return this.outfits;
	}

	

}
