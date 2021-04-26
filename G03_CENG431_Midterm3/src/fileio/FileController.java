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

	private static IContainer<User> users;
	private static IContainer<Outfit> outfits;
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
			outfits = fileIO.readOutfits("data\\outfits.json");
			users = fileIO.readUsers(outfits,"data\\users.xml");
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
	public static User getByUserName(String userName) throws ItemNotFoundException, NotSupportedException {
		return users.getByName(userName);
	}

	// get by product id
	public static Outfit getByOutfitId(String outfitId) throws ItemNotFoundException, NotSupportedException {
		return outfits.getById(outfitId);
	}

	public static IContainer<User> users() {
		return users;
	}

	public static IContainer<Outfit> outfits() {
		return outfits;
	}

	

}
