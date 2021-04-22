package factory;



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
	public CreationResult createOutfit(String type, String title, String id, String state);

	/**
	 * This function creates a user by given parameters
	 * 
	 * @param name     of the user
	 * @param type     of the user
	 * @param password of the user
	 * @returns creation result
	 */
	public CreationResult createUser(String name, String type, String password);



}
