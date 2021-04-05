package fileio;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import product.Assembly;
import product.Product;
import factory.CreationResult;
import factory.Creator;
public class ProductParser {

	private Creator creator;
	public ProductParser() {
		creator = new Creator();
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
		Product newProduct = null;
		while (keys.hasNext()) {
			Object keyTemp = keys.next();
			if(!(keyTemp instanceof String))
				//throw new JSONException(""); //Type cast bura galiba //eski olan
				throw new JSONException("ProductParser.recusiveParser::Key is not a string"); //Yeni olan
			String key = (String) keyTemp;
			val = jsonObject.get(key);
			String valType = val.getClass().getTypeName(); 
			
			if (valType.equals("org.json.JSONObject")) {
				JSONObject valObj = (JSONObject) val;
				String productType = valObj.get("type").toString();
				String title = key;
				String state = valObj.get("state").toString();
				String id = valObj.get("id").toString();
				CreationResult cr = creator.createProduct(productType,title,id,state); 
				newProduct = (Product) cr.object;
				if(newProduct == null)
					throw new JSONException("Wrong format "+cr.message);
				if(prd instanceof Assembly)
					((Assembly) prd).addProduct(newProduct);
				if(newProduct instanceof Assembly) {
					if (prd == null) {
						productList.add(newProduct);
					}
					recursiveParser(valObj, newProduct, productList);
				}

		}

	}
}}