package fileio.parser;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import factory.CreationResult;
import factory.ICreatorService;
import storage.IContainer;
import storage.UserContainer;
import user.User;

public class UserParser {

	protected UserParser() {

	}

	protected IContainer<User> parseUsers(String fileAll, ICreatorService creator) throws JSONException {
		JSONObject jsonUsers = (new JSONParser()).parse(fileAll);// parse all file
		IContainer<User> users = new UserContainer();// init user repo
		parse(jsonUsers, users, creator);// parse all to users
		return users;// return users

	}

	private void parse(JSONObject jsonObject, IContainer<User> users, ICreatorService creator) throws JSONException {
		Iterator<?> keys = jsonObject.keys();// get keys of json object
		Object val = null;
		User newUser = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();// get key
			if (!(keyTemp instanceof String))// if not an string
				throw new JSONException("UserParser.parse::Key is not a string");
			String key = (String) keyTemp;
			val = jsonObject.get(key);// get key value
			String valType = val.getClass().getTypeName();
			if (valType.equals("org.json.JSONObject")) {// if key json object
				JSONObject valObj = (JSONObject) val;
				String userType = valObj.get("type").toString();// get type key
				String name = key; // user name
				String password = valObj.get("password").toString();// get password key
				CreationResult cr = creator.createUser(name, userType, password); // pass to creator
				if (cr.object == null)// if null throw exception
					throw new JSONException("Wrong format " + cr.message);
				newUser = (User) cr.object;
				users.add(newUser);// add it to repo
			}
		}
	}
}
