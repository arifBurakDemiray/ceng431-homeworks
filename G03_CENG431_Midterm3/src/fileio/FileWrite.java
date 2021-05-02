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
	 * This function writes container items in json format to the file
	 * 
	 * @param <T>      type of gotten container
	 * @param items
	 * @param filePath
	 * @throws Exception for write operations
	 */

	protected <T> void writeItems(IContainer<T> items, String filePath) throws Exception {
		FileWriter fw = new FileWriter(filePath);// open file
		Writer writer = new BufferedWriter(fw);
		writer.write(convertToFormat(items));// write in json format or xml format according to container type
		writer.close();
	}

	/**
	 * According to container object, the function returns the string in xml or json
	 * format to write to the file.
	 * 
	 * @param <T>   type gotten container
	 * @param items
	 * @return
	 * @throws JSONException
	 */
	private <T> String convertToFormat(IContainer<T> items) throws JSONException {
		// get the type of items
		String typeName = items.getClass().getAnnotatedSuperclass().toString();
		String result = "";

		// According to the items' type, adjust string.
		if (typeName.contains("Outfit") || typeName.contains("Contract")) {
			String itemsString = items.toString();
			String jsonString = (new JSONParser()).parse(itemsString).toString(4);
			result = jsonString;
		}

		else if (typeName.contains("User")) {
			String xmlString = "<?xml version = \"1.0\"?>\n";
			xmlString += items.toString();
			result = xmlString;
		}
		return result;
	}
}
