package factory;

import product.Product;
import storage.IContainer;
import user.User;

/**
 * This class validates given attributes for creator
 */
public interface IValidatorService {

	/**
	 * This function validates a product attribute for creation
	 * 
	 * @param id   of the product
	 * @param type of the product
	 * @returns validation result
	 */
	public ValidationResult validateProduct(String id, String type);

	/**
	 * This function validates a product state
	 * 
	 * @param state of the product
	 * @returns validation result
	 */
	public ValidationResult validateState(String state);

	/**
	 * This function validates an id by uniquely
	 * 
	 * @param an id
	 * @returns validation result
	 */
	public ValidationResult validateId(String id);

	/**
	 * This function validates an user
	 * 
	 * @param name     of the user
	 * @param type     of the user
	 * @param password of the user
	 * @returns validation result
	 */
	public ValidationResult validateUser(String name, String type, String password);

	/**
	 * This function validates a user-product relation
	 * 
	 * @param userName  of the contract
	 * @param productId of the contract
	 * @param users     of the system
	 * @param products  of the system
	 * @returns validation result
	 */
	public ValidationResult validateContractProduct(String userName, String productId, IContainer<User> users,
			IContainer<Product> products);

	/**
	 * This function validates user-user relation
	 * 
	 * @param managerName of the contract
	 * @param userId      that is manager's employees' user names
	 * @param users       of the system
	 * @returns validation result
	 */
	public ValidationResult validateContractEmployee(String managerName, String[] userId, IContainer<User> users);

}
