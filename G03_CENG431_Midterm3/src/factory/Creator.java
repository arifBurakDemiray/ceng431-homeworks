package factory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import model.Collection;
import model.Outfit;
import model.User;
import storage.CollectionContainer;
import storage.IContainer;
import storage.StringContainer;

public class Creator implements ICreatorService {

	private final IValidatorService validator;

	public Creator() {
		validator = new Validator();
	}

	// this function creates a product
	public CreationResult createOutfit(String id, String brand_name, String gender, String type, String size,
			String color, String nofLikes, String nofDislikes) {
		ValidationResult vrId; // id validation result

		if (id == null) {
			id = RandomFactory.randomId();
			vrId = validator.validateId(id);
			while (!vrId.isValid()) {// if id null create a random id until it is valid
				id = RandomFactory.randomId();
				vrId = validator.validateId(id); // and validate it
			}
		} else { // if not null
			vrId = validator.validateId(id);
		}
		ValidationResult result = validator.validateOutfit(brand_name, gender, type, size, color, nofLikes,
				nofDislikes);// validate product

		String resultMessage = "";
		if (!vrId.isValid()) {
			resultMessage += vrId.message;
		}
		if (!result.isValid()) {
			resultMessage += result.message;
		}
		Outfit outfit = null;
		if (result.isValid() && vrId.isValid()) {
			String[] sizeArray = size.split(",");
			outfit = new Outfit(id, brand_name, type, gender, sizeArray, color, Integer.valueOf(nofLikes),
					Integer.valueOf(nofDislikes));
		} // return newly created product
		return new CreationResult(outfit, resultMessage);
	}

	// this function creates an user
	public CreationResult createUser(String name, String password, Element userNodeElement, String idsOfFollowers,
			String idsOfFollowings, IContainer<Outfit> outfits) { // pass to creator
		ValidationResult result = validator.validateUser(name, password); // validate inputs
		User usr = null;
		if (result.isValid()) {// if valid create user
			IContainer<Collection> collections = createCollections(userNodeElement, outfits);
			String[] followingsArray = idsOfFollowings.split(",");
			String[] followersArray = idsOfFollowers.split(",");

			IContainer<String> followings = createStringContainer(followingsArray);
			IContainer<String> followers = createStringContainer(followersArray);
			usr = new User(name, password, collections, followers, followings);
		} // return result
		return new CreationResult(usr, result.message);
	}

	private IContainer<String> createStringContainer(String[] arr) {
		IContainer<String> stringContainer = new StringContainer();
		for (int i = 0; i < arr.length; i++) {
			stringContainer.add(arr[i]);
		}

		return stringContainer;

	}

	private IContainer<Collection> createCollections(Element userNodeElement, IContainer<Outfit> outfits) {
		IContainer<Collection> collections = new CollectionContainer();
		NodeList nodesOfCollections = userNodeElement.getElementsByTagName("collections");
		int lengthOfCollections = nodesOfCollections.getLength();
		String outfitIds = "", collectionName = "";

		if (lengthOfCollections > 0) {
			for (int i = 0; i < lengthOfCollections; i++) {
				NodeList nodesOfCollection = userNodeElement.getElementsByTagName("collection");
				int lengthOfCollection = nodesOfCollection.getLength();
				for (int j = 0; j < lengthOfCollection; j++) {
					Node collectionNode = nodesOfCollection.item(j);
					if (collectionNode.getNodeType() == Node.ELEMENT_NODE) {

						Element collectionElement = (Element) collectionNode;
						collectionName = collectionElement.getAttribute("name");
						try {
							outfitIds = collectionElement.getElementsByTagName("ids").item(0).getTextContent();
						} catch (Exception e) {
							// TODO: handle exception
						}

						Collection collection = CreatorHelper.setCollection(collectionName, outfitIds, outfits);
						if (collection != null) {
							collections.add(collection);
						}
					}
				}

			}
		}

		return collections;
	}

}
