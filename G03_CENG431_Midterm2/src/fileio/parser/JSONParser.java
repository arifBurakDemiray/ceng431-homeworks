package fileio.parser;

import org.json.JSONException;
import org.json.JSONObject;


public class JSONParser {
	
	public JSONParser(){}
	
	public JSONObject parse(String file) throws JSONException{
		return new JSONObject(file);
		
	}
}