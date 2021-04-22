package fileio;

import model.Outfit;
import model.User;
import storage.IContainer;

/**
 * This class handles fileio operations
 */
public interface IFileIO {
	/**
	 * This function reads products
	 * 
	 * @param filePath of the products file
	 * @returns read products
	 * @throws Exception for fileio exceptions
	 */
	public IContainer<Outfit> readOutfits(String filePath) throws Exception;

	/**
	 * This function reads users
	 * 
	 * @param filePath of users file
	 * @returns read users
	 * @throws Exception for fileio exceptions
	 */
	public IContainer<User> readUsers(IContainer<Outfit> oufits, String filePath) throws Exception;

	/**
	 * This function writes users
	 * 
	 * @param users    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeUsers(IContainer<User> users, String filePath) throws Exception;

	/**
	 * This function writes products
	 * 
	 * @param products of the system
	 * @param filePath of the file
	 * @throws Exceptions for write operations
	 */
	public void writeOutfits(IContainer<Outfit> products, String filePath) throws Exception;

}
