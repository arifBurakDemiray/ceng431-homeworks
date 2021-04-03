package fileio;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import product.Assembly;
import product.Part;
import product.Product;

public class ProductParser {

	public ProductParser() {
	}

	public Collection<Product> parseProducts(String fileAll) throws JSONException {
		JSONObject jsonProducts = (new JSONParser()).parse(fileAll);
		return collectBaseProducts(jsonProducts);

	}

	private Collection<Product> collectBaseProducts(JSONObject jsonObject) throws JSONException {
		Collection<Product> products = new ArrayList<Product>();
		recursiveParser(jsonObject, null, products);
		return products;
	}

	private void recursiveParser(JSONObject jsonObject, Product prd, Collection<Product> productList)
			throws JSONException {
		// TYPE CAST EXCEPTION BAK 
		Iterator<?> keys = jsonObject.keys();
		Object val = null;
		Product newAssembly = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if(!(keyTemp instanceof String))
				throw new JSONException("");
			String key = (String) keyTemp;
			val = jsonObject.get(key);

			if (val.getClass().getTypeName().equals("org.json.JSONObject")) {

				if (((JSONObject) val).get("type").equals("Assembly")) {
					newAssembly = new Assembly(((JSONObject) val).get("id").toString(), key);
					if (prd == null) {
						productList.add(newAssembly);
					} else if (prd instanceof Assembly) {
						((Assembly) prd).addProduct(newAssembly);
					}

					recursiveParser(((JSONObject) val), newAssembly, productList);
				} else {
					newAssembly = new Part(((JSONObject) val).get("id").toString(), key);
					if (prd instanceof Assembly)
						((Assembly) prd).addProduct(newAssembly);
				}

			}

		}

	}
}