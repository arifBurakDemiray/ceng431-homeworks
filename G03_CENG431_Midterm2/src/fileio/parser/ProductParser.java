package fileio.parser;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

import product.Assembly;
import product.Product;
import storage.IContainer;
import storage.ProductContainer;
import factory.CreationResult;
import factory.ICreatorService;

public class ProductParser {

	protected ProductParser() {
	}

	protected IContainer<Product> parseProducts(String fileAll, ICreatorService creator) throws JSONException {
		JSONObject jsonProducts = (new JSONParser()).parse(fileAll);// parse file
		return collectBaseProducts(jsonProducts, creator);// collect base products

	}

	private IContainer<Product> collectBaseProducts(JSONObject jsonObject, ICreatorService creator)
			throws JSONException {
		IContainer<Product> products = new ProductContainer();// init product repo
		recursiveParser(jsonObject, null, products, creator);// null is for represent parent product, initially there is
																// no parent product
		return products;
	}

	private void recursiveParser(JSONObject jsonObject, Product prd, IContainer<Product> productList,
			ICreatorService creator) throws JSONException {
		Iterator<?> keys = jsonObject.keys();
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
	}
}