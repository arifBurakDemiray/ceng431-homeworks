package fileio;

import factory.ICreatorService;
import model.Outfit;
import model.User;
import storage.IContainer;
import contract.Contract;
import exception.FileFormatException;

/**
 * This class holds all informations
 */
public class FileController {

	private IContainer<User> users;
	private IContainer<Outfit> outfits;
	private IContainer<Contract> userLikeContracts;
	private IContainer<Contract> userDislikeContracts;
	private IFileIO fileIO;

	protected FileController(ICreatorService creator) {
		fileIO = new FileIO(creator);
	}

	/**
	 * This function reads all entities
	 * 
	 * @throws FileFormatException
	 */
	protected void readAll() throws FileFormatException {
		try {
			outfits = fileIO.readOutfits("data\\outfits.json");
			users = fileIO.readUsers(outfits,"data\\users.xml");
			userLikeContracts = fileIO.readContracts("data\\likes.json", users, outfits);
			userDislikeContracts = fileIO.readContracts("data\\dislikes.json", users, outfits);
		} catch (Exception e) {
			throw new FileFormatException(e.getMessage());
		}
	}

	/**
	 * This function writes all entities
	 * 
	 * @throws FileFormatException
	 */
	protected void writeAll() throws FileFormatException {
		try {
			fileIO.writeUsers(users, "data\\users.xml");
			fileIO.writeOutfits(outfits, "data\\outfits.json");
			fileIO.writeContracts(userLikeContracts, "data\\likes.json");
			fileIO.writeContracts(userDislikeContracts, "data\\dislikes.json");
		} catch (Exception e) {
			throw new FileFormatException(e.getMessage());
		}
	}
		

	protected IContainer<User> users() {
		return users;
	}

	protected IContainer<Contract> getUserLikeContracts() {
		return userLikeContracts;
	}


	public IContainer<Contract> getUserDislikeContracts() {
		return userDislikeContracts;
	}

	protected IContainer<Outfit> outfits() {
		return outfits;
	}

	

}
