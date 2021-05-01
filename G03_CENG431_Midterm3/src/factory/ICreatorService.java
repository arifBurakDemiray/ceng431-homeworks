package factory;

import org.w3c.dom.Element;

import fileio.parser.ContractParam;
import model.Outfit;
import storage.IContainer;

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
	public CreationResult createOutfit(String id, String brand_name, String gender, String type, String size,
			String color, String nofLikes, String nofDislikes);

	/**
	 * This function creates a user by given parameters
	 * 
	 * @param name     of the user
	 * @param password of the user
	 * @returns creation result
	 */
	public CreationResult createUser(String name, String password, Element userNodeElement, String idsOfFollowers,
			String idsOfFollowings, IContainer<Outfit> outfits);


	public CreationResult createContractUserOutfitsLikes(String userName, String outfitId, ContractParam contractParam);
	
}
