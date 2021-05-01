package fileio;

import contract.Contract;
import exception.FileFormatException;
import factory.Creator;
import model.Outfit;
import model.User;
import storage.IContainer;

public class BaseRepository {

	private static FileController fileController;

	public BaseRepository(){
		fileController = new FileController(new Creator());
	}
	
	public void initDatabase() throws FileFormatException{
		fileController.readAll();
	}
	
	protected static final IContainer<User> users(){
		return fileController.users();
	}
	
	protected static final IContainer<Outfit> outfits(){
		return fileController.outfits();
	}
	
	protected static final IContainer<Contract> likes(){
		return fileController.getUserLikeContracts();
	}
	protected static final IContainer<Contract> dislikes(){
		return fileController.getUserDislikeContracts();
	}
	
	
	protected static void saveChanges() throws FileFormatException{
		fileController.writeAll();
	}
}
