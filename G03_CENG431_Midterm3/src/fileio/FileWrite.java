package fileio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

import org.json.JSONException;

import fileio.parser.JSONParser;
import storage.IContainer;

public class FileWrite {

	protected FileWrite() {

	}

	/**
	 * This function writes container items in json format
	 * 
	 * @param <T>      type of the container
	 * @param items
	 * @param filePath
	 * @throws Exception for write operations
	 */

	protected <T> void writeItems(IContainer<T> items, String filePath) throws Exception {
		FileWriter fw = new FileWriter(filePath);// open file
		Writer writer = new BufferedWriter(fw);
		writer.write(convertToFormat(items));// write in json format
		writer.close();
	}
	
	private <T> String convertToFormat(IContainer<T> items) throws JSONException{
		String typeName = items.getClass().getAnnotatedSuperclass().toString();
		String result = "";
		if(typeName.contains("Outfit")){
			String itemsString = items.toString();
			String jsonString = (new JSONParser()).parse(itemsString).toString(4);
			result = jsonString;
		}
		else if(typeName.contains("User")){
			String xmlString = "<?xml version = \"1.0\"?>\n";
			xmlString += items.toString();
			result = xmlString;
		}
		//TODO more formats to come
		return result;
	}
}
