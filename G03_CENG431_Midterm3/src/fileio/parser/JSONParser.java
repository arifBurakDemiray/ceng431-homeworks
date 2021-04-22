package fileio.parser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class parses a given string to json
 */
public class JSONParser {

	public JSONParser() {
	}

	public JSONObject parse(String file) throws JSONException {
		return new JSONObject(file);// parse it to json
	}
}