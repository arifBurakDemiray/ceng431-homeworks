package fileio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

import org.json.JSONObject;

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
		String value = "{";
		value += items.toString();
		value += "}";
		JSONParser jsp = new JSONParser();// try to parse to json
		JSONObject jso = jsp.parse(value);
		FileWriter fw = new FileWriter(filePath);// open file
		Writer writer = new BufferedWriter(fw);
		writer.write(jso.toString(4));// write in json format
		writer.close();
	}
}
