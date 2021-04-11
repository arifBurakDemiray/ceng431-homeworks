package factory;

import product.Product;
import storage.IContainer;
import user.User;

/**
 * This class creates entities for the system
 */
public interface ICreatorService {

	/**
	 * This function creates a product by given parameters
	 * 
	 * @param type  of the product
	 * @param title of the product
	 * @param id    of the product
	 * @param state of the product
	 * @returns creation result
	 */
	public CreationResult createProduct(String type, String title, String id, String state);

	/**
	 * This function creates a user by given parameters
	 * 
	 * @param name     of the user
	 * @param type     of the user
	 * @param password of the user
	 * @returns creation result
	 */
	public CreationResult createUser(String name, String type, String password);

	/**
	 * This function creates a contract between user and product
	 * 
	 * @param userName  of the user
	 * @param productId of the product
	 * @param users     of the system
	 * @param products  of the system
	 * @returns creation result
	 */
	public CreationResult createContractUserProduct(String userName, String productId, IContainer<User> users,
			IContainer<Product> products);

	/**
	 * This function creates a contract between manager and his employees
	 * 
	 * @param managerName of the manager
	 * @param userIds     of the employees names
	 * @param users       of the system
	 * @returns creation result
	 */
	public CreationResult createContractManagerEmplooye(String managerName, String[] userId, IContainer<User> users);

}
