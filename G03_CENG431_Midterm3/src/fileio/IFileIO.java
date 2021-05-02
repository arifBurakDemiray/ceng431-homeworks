package fileio;

import contract.Contract;
import model.Outfit;
import model.User;
import storage.IContainer;

/**
 * This class handles fileio operations
 */
public interface IFileIO {
	/**
	 * This function reads outfits
	 * 
	 * @param filePath of the outfits file
	 * @returns read outfits
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
	 * This function reads contracts
	 * 
	 * @param filePath of the contracts file
	 * @param users    of the system
	 * @param outfits  of the system
	 * @returns read contracts
	 * @throws Exception for fileio exceptions
	 */
	public IContainer<Contract> readContracts(String filePath, IContainer<User> users, IContainer<Outfit> outfits)
			throws Exception;

	/**
	 * This function writes users
	 * 
	 * @param users    of the system
	 * @param filePath of the file
	 * @throws Exception for write operations
	 */
	public void writeUsers(IContainer<User> users, String filePath) throws Exception;

	/**
	 * This function writes outfits
	 * 
	 * @param outfits  of the system
	 * @param filePath of the file
	 * @throws Exceptions for write operations
	 */
	public void writeOutfits(IContainer<Outfit> outfits, String filePath) throws Exception;

	/**
	 * This function writes contracts
	 * 
	 * @param contracts of the system
	 * @param filePath  of the file
	 * @throws Exceptions for write operations
	 */
	public void writeContracts(IContainer<Contract> contracts, String filePath) throws Exception;

}
