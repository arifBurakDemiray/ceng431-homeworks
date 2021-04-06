package fileio;

import factory.Creator;
import product.Product;
import storage.IContainer;
import user.User;
import contract.Contract;
import exception.FileFormatException;
public class FileController {

	private IContainer<User> users;
	private IContainer<Product> products;
	private IContainer<Contract> contracts;
	private IFileIO fileIO;
	public FileController(Creator creator) {
		this.fileIO = new FileIO(creator);
	}
	
	public void readAll() throws FileFormatException {
		try {
			users = fileIO.readUsers("data\\users.json");
			products = fileIO.readProducts("data\\products.json");
			contracts = fileIO.readContracts("data\\contracts.json", users, products);
		}
		catch(Exception e) {
			throw new FileFormatException(e.getMessage());
		}
	}
	
	public void writeAll() throws FileFormatException {
		try {
			fileIO.writeUsers(users, "data\\users.json");
			fileIO.writeProducts(products, "data\\products.json");
			fileIO.writeContracts(contracts, "data\\contracts.json");
		} catch (Exception e) {
			throw new FileFormatException(e.getMessage());
		}
	}
	
	public IContainer<User> users(){
		return users;
	}
}
