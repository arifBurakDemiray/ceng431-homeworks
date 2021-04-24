package fileio.parser;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

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
	protected UserParser() {

	}

	protected IContainer<User> parseUsers(String fileAll,ICreatorService creator,IContainer<Outfit> outfits)
			throws Exception {
		
		this.creator = creator;
		this.outfits = outfits;
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new StringReader(fileAll)));
		IContainer<User> users = new UserContainer();// init user repo
		parse(doc, users);// parse all to users
		return users;// return users

	}

	private void parse(Document doc, IContainer<User> users) throws Exception {

		doc.getDocumentElement().normalize();
		if (!doc.getDocumentElement().getNodeName().equals("users"))
			throw new FileFormatException("File is not in initial format");
		NodeList nodeListOfUsers = doc.getElementsByTagName("user");

		for (int i = 0; i < nodeListOfUsers.getLength(); i++) {
			Node userNode = nodeListOfUsers.item(i);
			if (userNode.getNodeType() == Node.ELEMENT_NODE) {
				Element userNodeElement = (Element) userNode;
				User user = createUser(userNodeElement);
				users.add(user);
			}
		}
	}

	private User createUser(Element userNodeElement) throws SAXException {
		String userName, password = "";
		String idsOfFollowings = "";
		String idsOfFollowers = "";
		userName = userNodeElement.getAttribute("userName");
		
		password = getTagValue("password", userNodeElement);
		idsOfFollowings = getTagValue("followings", userNodeElement);
		idsOfFollowers = getTagValue("followers", userNodeElement);
	
		
		User user;
		CreationResult cr = creator.createUser(userName, password, userNodeElement, idsOfFollowers,idsOfFollowings ,outfits); // pass to creator
		if (cr.object == null)// if null throw exception
			throw new SAXException("Wrong format " + cr.message);
		user = (User) cr.object;
		
		return user;
	}
	
	// gets value by tag name
	private static String getTagValue(String tag, Element element) {
		
		if(element.getElementsByTagName(tag).item(0).getTextContent() != null)
		{
			return element.getElementsByTagName(tag).item(0).getTextContent();
		}
			
		else
		{
			return "";
		}
	}
	
}
