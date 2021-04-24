package factory;


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

	

}
