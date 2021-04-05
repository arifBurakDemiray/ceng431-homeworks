package fileio;


import java.util.Collection;

import product.Product;


public class FileIO implements IFileIO {
	private FileRead fRead;
	//private FileWrite fWrite;

	public FileIO() {
		this.fRead = new FileRead(); // initialise file read
		//this.fWrite = new FileWrite(); // initialise file write
	}

	public Collection<Product> readProducts(String filePath) throws Exception {
		String fileAll = fRead.readFile(filePath);
		return (new ProductParser()).parseProducts(fileAll);
	}

	

}
