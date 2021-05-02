package fileio;

import contract.Contract;
import exception.FileFormatException;
import factory.Creator;
import model.Outfit;
import model.User;
import storage.IContainer;

public class BaseRepository {
	
	// FileController to read data, write data and get read content of files.
	private static FileController fileController;

	public BaseRepository(){
		fileController = new FileController(new Creator());
	}
	
	/**
	 * The function reads all files and initialises containers.
	 * @throws FileFormatException
	 */
	public void initDatabase() throws FileFormatException{
		fileController.readAll();
	}
	
	/**
	 * The function returns the read users' container
	 * @return  User Container
	 */
	protected static final IContainer<User> users(){
		return fileController.users();
	}
	
	/**
	 * The function returns the read outfits' container
	 * @return  Outfit Container
	 */
	protected static final IContainer<Outfit> outfits(){
		return fileController.outfits();
	}
	
	/**
	 * The function returns the container of read like contracts of users'
	 * @return  Like Contract Container
	 */
	protected static final IContainer<Contract> likes(){
		return fileController.getUserLikeContracts();
	}
	
	/**
	 * The function returns the container of read dislike contracts of users'
	 * @return Dislike Contract Container
	 */
	protected static final IContainer<Contract> dislikes(){
		return fileController.getUserDislikeContracts();
	}
	
	/**
	 * The function writes all data to necessary files.
	 */
	protected static void saveChanges() throws FileFormatException{
		fileController.writeAll();
	}
}
