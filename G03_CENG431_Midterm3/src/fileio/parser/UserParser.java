package fileio.parser;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import exception.FileFormatException;
import factory.CreationResult;
import factory.ICreatorService;
import model.Outfit;
import model.User;
import storage.IContainer;
import storage.UserContainer;

public class UserParser {
	private ICreatorService creator;
	private IContainer<Outfit> outfits;

	/**
	 * The UserParser parses the gotten contract file content and creates user
	 * objects
	 */
	protected UserParser() {

	}

	/**
	 * The function parses gotten file content and returns the user container which
	 * holds created users
	 * 
	 * @param fileAll = user file content
	 * @param creator = creator object
	 * @param outfits = outfit container which holds all outfits
	 * @return User Container
	 * @throws XMLException
	 */
	protected IContainer<User> parseUsers(String fileAll, ICreatorService creator, IContainer<Outfit> outfits)
			throws Exception {

		this.creator = creator;
		this.outfits = outfits;
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new StringReader(fileAll)));
		IContainer<User> users = new UserContainer();// initialise user container
		parse(doc, users);// parse all to users
		return users;// return users

	}

	/**
	 * The function parses gotten Document object and creates users. After creating
	 * each user, add the user to the users
	 * 
	 * @param doc   = Document object of file content
	 * @param users = contract container
	 * @throws JSONException
	 */
	private void parse(Document doc, IContainer<User> users) throws Exception {

		doc.getDocumentElement().normalize();
		if (!doc.getDocumentElement().getNodeName().equals("users"))
			throw new FileFormatException("File is not in initial format");
		// get user nodes of xml.
		NodeList nodeListOfUsers = doc.getElementsByTagName("user");

		// travel to each user
		for (int i = 0; i < nodeListOfUsers.getLength(); i++) {
			// get user node
			Node userNode = nodeListOfUsers.item(i);
			if (userNode.getNodeType() == Node.ELEMENT_NODE) {
				Element userNodeElement = (Element) userNode;
				// create user invoking createUser()
				User user = createUser(userNodeElement);
				users.add(user); // add created user to the users
			}
		}
	}

	/**
	 * The function creates a user according to userNodeElement values
	 * 
	 * @param userNodeElement = user node element
	 * @return User
	 * @throws SAXException
	 */
	private User createUser(Element userNodeElement) throws SAXException {
		String userName, password = "";
		String idsOfFollowings = "";
		String idsOfFollowers = "";
		// get values
		userName = userNodeElement.getAttribute("userName");
		password = getTagValue("password", userNodeElement);
		idsOfFollowings = getTagValue("followings", userNodeElement);
		idsOfFollowers = getTagValue("followers", userNodeElement);

		User user;

		// Create a user using creator
		CreationResult cr = creator.createUser(userName, password, userNodeElement, idsOfFollowers, idsOfFollowings,
				outfits); // pass to creator
		if (cr.object == null)// if creation is failed, throw exception
			throw new SAXException("Wrong format " + cr.message);
		user = (User) cr.object;

		return user;
	}

	// gets value by tag name
	private static String getTagValue(String tag, Element element) {
		try {
			return element.getElementsByTagName(tag).item(0).getTextContent();
		} catch (Exception e) {
			return "";
		}
	}

}
