package fileio;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import factory.CreationResult;
import factory.Creator;
import storage.IContainer;
import storage.UserContainer;
import user.User;

public class UserParser {

	public UserParser() {

	}

	public IContainer<User> parseUsers(String fileAll, Creator creator) throws JSONException {
		JSONObject jsonUsers = (new JSONParser()).parse(fileAll);
		IContainer<User> users = new UserContainer();
		parse(jsonUsers, users, creator);
		return users;

	}

	private void parse(JSONObject jsonObject, IContainer<User> users, Creator creator)
			throws JSONException {
		// TYPE CAST EXCEPTION BAK
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		User newUser = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				// throw new JSONException(""); //Type cast bura galiba //eski olan
				throw new JSONException("UserParser.parse::Key is not a string"); // Yeni olan
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			String valType = val.getClass().getTypeName();
			if (valType.equals("org.json.JSONObject")) {
				JSONObject valObj = (JSONObject) val;
				String userType = valObj.get("type").toString();
				String name = key;
				String password = valObj.get("password").toString();
				CreationResult cr = creator.createUser(name, userType, password);
				newUser = (User) cr.object;
				if (newUser == null)
					throw new JSONException("Wrong format " + cr.message);

			}
		}
	}
}
