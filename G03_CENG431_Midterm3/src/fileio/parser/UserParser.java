package fileio.parser;


import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import exception.FileFormatException;
import factory.ICreatorService;
import model.Collection;
import model.User;
import storage.CollectionContainer;
import storage.IContainer;
import storage.UserContainer;

public class UserParser {

	protected UserParser() {

	}

	protected IContainer<User> parseUsers(String fileAll, ICreatorService creator)
			throws Exception {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new StringReader(fileAll)));
		IContainer<User> users = new UserContainer();// init user repo
		parse(doc, users, creator);// parse all to users
		return users;// return users

	}

	private void parse(Document doc, IContainer<User> users, ICreatorService creator) throws Exception {

		doc.getDocumentElement().normalize();
		if (!doc.getDocumentElement().getNodeName().equals("users"))
			throw new FileFormatException("File is not in initial format");
		NodeList nList = doc.getElementsByTagName("user");
		String userName,password,followings,followers = "";
		IContainer<Collection> collections = new CollectionContainer();
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				userName = eElement.getAttribute("userName");
				password = eElement.getElementsByTagName("password").item(0).getTextContent();
				followings = eElement.getElementsByTagName("followings").item(0).getTextContent();
				followers = eElement.getElementsByTagName("followers").item(0).getTextContent();
				
				System.out.println(userName + "-" + password.hashCode() + "-" + followings + "-" + followers );
			}
		}

	}
}
