package fileio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;


import org.json.JSONObject;

import fileio.parser.JSONParser;
import storage.IContainer;

public class FileWrite {

	public FileWrite() {
		
	}
	
	public <T> void writeItems(IContainer<T> items,String filePath) throws Exception {
		String value = "{";
		value+=items.toString();
		value+="}";
		JSONParser jsp = new JSONParser();
		JSONObject jso = jsp.parse(value);
		FileWriter fw = new FileWriter(filePath);
		Writer writer = new BufferedWriter(fw);
		writer.write(jso.toString(4));
		writer.close();
	}
}
