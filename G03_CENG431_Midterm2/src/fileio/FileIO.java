package fileio;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

import product.Product;


public class FileIO  {
	private FileRead fRead;
	private FileWrite fWrite;

	public FileIO() {
		this.fRead = new FileRead(); // initialise file read
		this.fWrite = new FileWrite(); // initialise file write
	}

	public Collection<Product> readProducts(String filePath) throws IOException,JSONException {
		String fileAll = fRead.readFile(filePath);
		return (new ProductParser()).parseProducts(fileAll);
	}

	

}
