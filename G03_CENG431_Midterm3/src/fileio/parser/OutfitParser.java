package fileio.parser;


import storage.IContainer;


import factory.ICreatorService;
import model.Outfit;

public class OutfitParser {

	protected OutfitParser() {
	}

	protected IContainer<Outfit> parseOutfits(String fileAll, ICreatorService creator){
		/*JSONObject jsonOutfits = (new JSONParser()).parse(fileAll);// parse file
		Iterator<?> keys = jsonOutfits.keys();
		Object val = null; // same logic as user parser
		Product newProduct = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if (!(keyTemp instanceof String))
				throw new JSONException("ProductParser.recusiveParser::Key is not a string");
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			String valType = val.getClass().getTypeName();

			if (valType.equals("org.json.JSONObject")) {
				JSONObject valObj = (JSONObject) val;
				String productType = valObj.get("type").toString(); // get type value
				String id = key;
				String state = valObj.get("state").toString(); // get state value
				String title = valObj.get("name").toString(); // get name value
				CreationResult cr = creator.createProduct(productType, title, id, state); // pass to creator
				newProduct = (Product) cr.object;
				if (newProduct == null) // if null
					throw new JSONException("Wrong format " + cr.message);
				if (prd instanceof Assembly) // if parent assembly
					((Assembly) prd).addProduct(newProduct);
				if (prd == null) // if parent null means main product
					productList.add(newProduct);
				if (newProduct instanceof Assembly) { // if created assembly recurse again
					recursiveParser(valObj, newProduct, productList, creator);
				}

			}

		}
	}*/
	
		return null;

	}

	
}