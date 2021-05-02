package factory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import contract.Contract;
import contract.ContractUserOutfitsLikes;
import fileio.parser.ContractParam;
import model.Collection;
import model.Outfit;
import model.User;
import storage.CollectionContainer;
import storage.IContainer;
import storage.StringContainer;

public class Creator implements ICreatorService {

	private final IValidatorService validator; // it provides validation for creation.

	public Creator() {
		validator = new Validator();
	}

	// this function creates a outfit if validation is provided
	public CreationResult createOutfit(String id, String brand_name, String gender, String type, String size,
			String color, String nofLikes, String nofDislikes) {
		ValidationResult vrId; // validation result for id

		if (id == null) {
			id = RandomFactory.randomId();
			vrId = validator.validateId(id);
			while (!vrId.isValid()) {// if id is null create a random id until it is valid ( unique )
				id = RandomFactory.randomId();
				vrId = validator.validateId(id); // and validate it
			}
		} else { // if it is not null validate it
			vrId = validator.validateId(id);
		}
		ValidationResult result = validator.validateOutfit(brand_name, gender, type, size, color, nofLikes,
				nofDislikes);// validate outfit

		String resultMessage = "";
		if (!vrId.isValid()) {
			resultMessage += vrId.message;
		}
		if (!result.isValid()) {
			resultMessage += result.message;
		}
		Outfit outfit = null;
		// If validation is okey, create the outfit with the taken parameters.
		if (result.isValid() && vrId.isValid()) {
			String[] sizeArray = size.split(",");
			outfit = new Outfit(id, brand_name, type, gender, sizeArray, color, Integer.valueOf(nofLikes),
					Integer.valueOf(nofDislikes));
		} // return newly created outfit
		return new CreationResult(outfit, resultMessage);
	}

	// this function creates an user
	public CreationResult createUser(String name, String password, Element userNodeElement, String idsOfFollowers,
			String idsOfFollowings, IContainer<Outfit> outfits) {
		ValidationResult result = validator.validateUser(name, password); // validate that inputs are valid
		User usr = null;
		if (result.isValid()) {// if name and password are valid, create user

			// to create the collections, pass the xml node element to helper function
			// createCollections()
			IContainer<Collection> collections = createCollections(userNodeElement, outfits);

			String[] followingsArray = idsOfFollowings.split(",");
			String[] followersArray = idsOfFollowers.split(",");

			// to create the container of followings and followers, pass the array to helper
			// function createStringContainer()
			IContainer<String> followings = createStringContainer(followingsArray);
			IContainer<String> followers = createStringContainer(followersArray);
			usr = new User(name, password, collections, followers, followings);
		} // return result
		return new CreationResult(usr, result.message);
	}

	/**
	 * The function gets the string array and return a String Container with values
	 * in the string array.
	 * 
	 * @param arr String array
	 * @return String Container
	 */
	private IContainer<String> createStringContainer(String[] arr) {
		IContainer<String> stringContainer = new StringContainer();
		for (int i = 0; i < arr.length; i++) {
			stringContainer.add(arr[i]);
		}

		return stringContainer;

	}

	/**
	 * The function gets the xml node element and create the collections according
	 * to outfit data in the element.
	 * 
	 * @param userNodeElement = xml node elements
	 * @param outfits         = container of all outfits
	 * @return Collection Container which contains collections
	 */
	private IContainer<Collection> createCollections(Element userNodeElement, IContainer<Outfit> outfits) {
		IContainer<Collection> collections = new CollectionContainer();

		// Get the collection"s" node list of xml and travel to every collection in the
		// list
		NodeList nodesOfCollections = null;
		int lengthOfCollections=0;
		try {
			nodesOfCollections = userNodeElement.getElementsByTagName("collections");
			lengthOfCollections = nodesOfCollections.getLength();
		} catch (Exception e) {
		}
		String outfitIds = "", collectionName = "";

		if (lengthOfCollections > 0) {
			for (int i = 0; i < lengthOfCollections; i++) {
				// Get the collection
				NodeList nodesOfCollection = userNodeElement.getElementsByTagName("collection");
				int lengthOfCollection = nodesOfCollection.getLength();

				// Get the outfits of collection and travel to every outfit in the collection
				for (int j = 0; j < lengthOfCollection; j++) {
					Node collectionNode = nodesOfCollection.item(j);
					if (collectionNode.getNodeType() == Node.ELEMENT_NODE) {
						Element collectionElement = (Element) collectionNode;

						// Get the variables
						collectionName = collectionElement.getAttribute("name");
						try {
							outfitIds = collectionElement.getElementsByTagName("ids").item(0).getTextContent();
						} catch (Exception e) {
						}

						// Create the collection using CreatorHelper.setCollection()
						Collection collection = CreatorHelper.setCollection(collectionName, outfitIds, outfits);
						if (collection != null) {
							collections.add(collection); // if collection is created successfully, add it the the
															// collection container.
						}
					}
				}
			}
		}

		return collections;
	}

	// The function creates a contract between user and his/her liked, disliked
	// outfits.
	// The contract help us to detect which user liked/disliked which outfit to
	// prevent like/dislike spam in the program.
	public CreationResult createContractUserOutfitsLikes(String userName, String outfitsId,
			ContractParam contractParam) {

		Contract contract = null;
		// Control that gotten user name is a valid user name.
		ValidationResult resultUser = validator.validateUsername(userName, contractParam.getUsers());
		if (!resultUser.isValid()) {
			return new CreationResult(contract, resultUser.message); // if not valid, create result as failed.
		}

		// Control that the gotten outfits ids are valid. Add the valid ids to the
		// contractOutfits string container.
		String[] ids = outfitsId.split(",");
		IContainer<String> contractOutfits = new StringContainer();
		for (String id : ids) {
			// validate inputs
			ValidationResult resultId = validator.validateContractUserOutfitsLikes(id, contractParam.getOutfits());
			if (resultId.isValid()) {// if is valid
				contractOutfits.add(id);
			}
		}

		// After validation, set the contract with user name and string container of
		// outfits ids.
		contract = new ContractUserOutfitsLikes(userName, contractOutfits);
		return new CreationResult(contract, resultUser.message);
	}

}
