package fileio;

import contract.Contract;
import product.Product;
import storage.IContainer;
import user.User;

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
	public IContainer<Product> readProducts(String filePath) throws Exception;

	/**
	 * This function reads users
	 * 
	 * @param filePath of users file
	 * @returns read users
	 * @throws Exception for fileio exceptions
	 */
	public IContainer<User> readUsers(String filePath) throws Exception;

	/**
	 * This function reads contracts
	 * 
	 * @param filePath of the contracts file
	 * @param users    of the system
	 * @param products of the system
	 * @returns read contracts
	 * @throws Exception for fileio exceptions
	 */
	public IContainer<Contract> readContracts(String filePath, IContainer<User> users, IContainer<Product> products)
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
	 * This function writes products
	 * 
	 * @param products of the system
	 * @param filePath of the file
	 * @throws Exceptions for write operations
	 */
	public void writeProducts(IContainer<Product> products, String filePath) throws Exception;

	/**
	 * This function writes contracts
	 * 
	 * @param contracts of the system
	 * @param filePath  of the file
	 * @throws Exceptions for write operations
	 */
	public void writeContracts(IContainer<Contract> contracts, String filePath) throws Exception;
}
