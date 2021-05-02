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
		IContainer<Outfit> outfits = new OutfitContainer();// initialise outfit container
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
			if (!(keyTemp instanceof String))// if key is not a string, throw exception
				throw new JSONException("OutfitParser.parse::Key is not a string");

			String key = (String) keyTemp;
			val = jsonObject.get(key);// get key value
			String valType = val.getClass().getTypeName();
			if (valType.equals("org.json.JSONObject")) {// if key is a json object
				JSONObject valObj = (JSONObject) val;
				String id = key; // get outfit id
				String brandName = valObj.get("brand_name").toString();// get brand name
				String gender = valObj.get("gender").toString(); // get gender
				String type = valObj.get("type").toString(); // get type
				String size = valObj.get("size").toString(); // get size
				String color = valObj.get("color").toString(); // get color
				String numberOfLikes = "0";
				String numberOfDislikes = "0";
				try {
					numberOfLikes = valObj.get("noflikes").toString(); // get number of likes
				} catch (JSONException e) {
				}
				try {
					numberOfDislikes = valObj.get("nofdislikes").toString(); // get number of dislikes
				} catch (JSONException e) {
				}

				CreationResult cr = creator.createOutfit(id, brandName, gender, type, size, color, numberOfLikes,
						numberOfDislikes); // pass to creator
				if (cr.object == null)// if creation result is fail, throw exception
					throw new JSONException("Wrong format " + cr.message);

				// else get the comment of outfit
				newOutfit = (Outfit) cr.object;
				Object comments = null;
				try {
					comments = valObj.get("comments");
				} catch (JSONException e) {
				}

				// set comment container of outfit
				extractComments(comments, newOutfit);

				outfits.add(newOutfit);// add it to outfit container
			}
		}
	}

	private void extractComments(Object comments, Outfit outfit) {
		if (comments != null) {
			String valType = comments.getClass().getTypeName();

			if (valType.equals("org.json.JSONArray")) {

				// travel to each comment in the comment json array
				JSONArray commnetsArr = (JSONArray) comments;
				int length = commnetsArr.length();

				for (int i = 0; i < length; i++) {
					JSONObject obj;
					try {
						// try to get comment and add the comment to the outfit's comment container
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