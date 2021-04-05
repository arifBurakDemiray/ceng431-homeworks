package fileio;


import factory.Creator;
import product.Product;
import storage.IContainer;
import user.User;


public class FileIO implements IFileIO {
	private FileRead fRead;
	private Creator creator;
	//private FileWrite fWrite;

	public FileIO() {
		this.fRead = new FileRead(); // initialise file read
		this.creator = new Creator();
		//this.fWrite = new FileWrite(); // initialise file write
	}

	public IContainer<Product> readProducts(String filePath) throws Exception {
		String fileAll = fRead.readFile(filePath);
		return (new ProductParser()).parseProducts(fileAll,this.creator);
	}

	@Override
	public IContainer<User> readUser(String filePath) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
