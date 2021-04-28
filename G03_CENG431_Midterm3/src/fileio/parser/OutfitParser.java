package fileio.parser;

import storage.IContainer;
import storage.OutfitContainer;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import factory.CreationResult;
import factory.ICreatorService;
import model.Comment;
import model.Outfit;

/**
 * This class parses outfits.json to objects
 */
public class OutfitParser {

	/**
	 * Constructor
	 */
	protected OutfitParser() {
	}

	protected IContainer<Outfit> parseOutfits(String fileAll, ICreatorService creator) throws JSONException {
		JSONObject jsonOutfits = (new JSONParser()).parse(fileAll);// parse all file
		IContainer<Outfit> outfits = new OutfitContainer();// init outfit repo
		parse(jsonOutfits, outfits, creator);// parse all to outfits
		return outfits;// return outfits
	}

	private void parse(JSONObject jsonObject, IContainer<Outfit> outfits, ICreatorService creator)
			throws JSONException {
		Iterator<?> keys = jsonObject.keys();// get keys of json object
		Object val = null;
		Outfit newOutfit = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();// get key
			if (!(keyTemp instanceof String))// if not an string
				throw new JSONException("OutfitParser.parse::Key is not a string");
			String key = (String) keyTemp;
			val = jsonObject.get(key);// get key value
			String valType = val.getClass().getTypeName();
			if (valType.equals("org.json.JSONObject")) {// if key json object
				JSONObject valObj = (JSONObject) val;
				String id = key; // outfit id
				String brandName = valObj.get("brand_name").toString();// get brand name key
				String gender = valObj.get("gender").toString(); // get gender key
				String type = valObj.get("type").toString(); // get type key
				String size = valObj.get("size").toString(); // get size key
				String color = valObj.get("color").toString(); // get size key
				String numberOfLikes = "";
				String numberOfDislikes = "";
				try {
					numberOfLikes = valObj.get("noflikes").toString(); // get size key
				} catch (JSONException e) {
				}
				try {
					numberOfDislikes = valObj.get("nofdislikes").toString(); // get size key
				} catch (JSONException e) {
				}

				CreationResult cr = creator.createOutfit(id, brandName, gender, type, size, color, numberOfLikes,
						numberOfDislikes); // pass to creator
				if (cr.object == null)// if null throw exception
					throw new JSONException("Wrong format " + cr.message);
				newOutfit = (Outfit) cr.object;
				Object comments = null;
				try {
					comments = valObj.get("comments");
				} catch (JSONException e) {
				}

				extractComments(comments, newOutfit);

				outfits.add(newOutfit);// add it to repo
			}
		}
	}

	private void extractComments(Object comments, Outfit outfit) {
		if (comments != null) {
			String valType = comments.getClass().getTypeName();
			if (valType.equals("org.json.JSONArray")) {
				JSONArray commnetsArr = (JSONArray) comments;
				int length = commnetsArr.length();
				// Iterator<?> keys = commnetsO.keys();// get keys of json object
				for (int i = 0; i < length; i++) {
					// Object keyTemp =() keys.next();// get key
					JSONObject obj;
					try {
						obj = commnetsArr.getJSONObject(i);
						String userName = (String) obj.keys().next();
						if (userName instanceof String) {
							try {
								outfit.getComments()
										.add(new Comment((String) userName, obj.get((String) userName).toString()));
							} catch (JSONException e) {

							}
						}
					} catch (JSONException e1) {

					}

				}
			}
		}
	}
}