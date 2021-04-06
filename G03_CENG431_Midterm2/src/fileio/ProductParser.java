package fileio;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

import product.Assembly;
import product.Product;
import storage.IContainer;
import storage.ProductContainer;
import factory.CreationResult;
import factory.Creator;
public class ProductParser {

	public ProductParser() {
	}

	public IContainer<Product> parseProducts(String fileAll, Creator creator) throws JSONException {
		JSONObject jsonProducts = (new JSONParser()).parse(fileAll);
		return collectBaseProducts(jsonProducts,creator);

	}

	private IContainer<Product> collectBaseProducts(JSONObject jsonObject,Creator creator) throws JSONException {
		IContainer<Product> products = new ProductContainer();
		recursiveParser(jsonObject, null, products,creator);
		return products;
	}

	private void recursiveParser(JSONObject jsonObject, Product prd, IContainer<Product> productList,Creator creator)
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
				if(prd==null)
					productList.add(newProduct);
				if(newProduct instanceof Assembly) {
					recursiveParser(valObj, newProduct, productList,creator);
				}

		}

	}
}}