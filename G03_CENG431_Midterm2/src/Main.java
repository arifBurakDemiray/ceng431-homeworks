import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.*;

public class Main {
	public static void main(String[] args) throws JSONException {
		org.json.JSONArray js;
		File file = new File("data\\deneme.json"); // opening file
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String fileAll = "";
			String line;
			while ((line = br.readLine()) != null) {
				fileAll += line;
			}
			br.close();

			JSONObject object = new JSONObject(fileAll);
			String[] keys = JSONObject.getNames(object);
			JSONTokener jst;
		
			for (String key : keys) {
				JSONObject value = (JSONObject) object.get(key);
				jst = new JSONTokener(value.toString());
				JSONObject obj = (JSONObject)jst.nextValue();
				for (String string : JSONObject.getNames(obj)) {
					System.out.println(string);
				}
				
				// Determine type of value and do something with it...
			}
			/*
			//
			JSONObject jsObject = new JSONObject(fileAll);
			// JSONObject next = it.next();
			loopThroughJson("", jsObject);*/

			// JSONObject js1 = js.getJSONObject("Engine");
			// System.out.println(((JSONObject) js1.get("EngineBlock")).get("type"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loopThroughJson(String keyOb, Object input) throws JSONException {

		if (input instanceof JSONObject) {

			Iterator<?> keys = ((JSONObject) input).keys();

			while (keys.hasNext()) {

				String key = (String) keys.next();
				loopThroughJson(key, ((JSONObject) input).get(key));

			}

		} else {
			System.out.println(keyOb + "=" + input.toString());
		}

	}
}
