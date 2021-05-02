package factory;

import model.Outfit;
import model.User;
import storage.IContainer;

/**
 * This class validates given attributes for creator
 */
public interface IValidatorService {

	/**
	 * This function validates a outfit attribute for creation
	 * 
	 * @param id   of the outfit
	 * @param type of the outfit
	 * @returns validation result
	 */
	public ValidationResult validateOutfit(String brand_name, String gender, String type, String size,
			String color, String nofLikes, String nofDislikes);

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
	 * @param password of the user
	 * @returns validation result
	 */
	public ValidationResult validateUser(String name, String password);
	
	/**
	 * The function validates the gotten outfit id. It searches the gotten id in the outfit container
	 * @param outfitId = id to validate
	 * @param outfits = container of all outfits
	 * @return validation result
	 */
	public ValidationResult validateContractUserOutfitsLikes(String outfitId,IContainer<Outfit> outfits);
	
	/**
	 * The function validates the gotten user name. It searches the gotten user name in the user container
	 * @param username = user name to validate
	 * @param users = container of all users
	 * @return validation result
	 */
	public ValidationResult validateUsername(String username,IContainer<User> users);

}
