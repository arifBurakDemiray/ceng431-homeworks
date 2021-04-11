package fileio;

import java.io.IOException;

import contract.Contract;
import factory.ICreatorService;
import fileio.parser.Parser;
import product.Product;
import storage.ContractContainer;
import storage.IContainer;
import storage.ProductContainer;
import storage.UserContainer;
import user.Admin;
import user.User;

public class FileIO implements IFileIO {
	private FileRead fRead;
	private ICreatorService creator;
	private FileWrite fWrite;
	private Parser parser;

	public FileIO(ICreatorService creator) {
		this.fRead = new FileRead(); // initialise file read
		this.creator = creator;
		this.fWrite = new FileWrite(); // initialise file write
		this.parser = new Parser();
	}

	@Override
	public IContainer<Product> readProducts(String filePath) throws Exception {
		IContainer<Product> products = null;
		try {
			String fileAll = fRead.readFile(filePath);// read file
			if (!fileAll.isBlank())// if not blank
				products = parser.parseProducts(fileAll, this.creator); // parse products
			else
				products = new ProductContainer(); // init empty repo
		} catch (IOException e) {
			products = new ProductContainer(); // init empty repo
		}

		return products;
	}

	@Override
	public IContainer<User> readUsers(String filePath) throws Exception {
		IContainer<User> users = null;
		try {
			String fileAll = fRead.readFile(filePath); // read file
			if (!fileAll.isBlank()) // if not blank
				users = parser.parseUsers(fileAll, this.creator); // parse users
			else
				users = new UserContainer(); // init empty repo
		} catch (IOException e) {
			users = new UserContainer();// init empty repo
		}

		users.add(new Admin("SYSADMIN", "SYSADMIN")); // add admin to the system
		return users;
	}

	@Override
	public IContainer<Contract> readContracts(String filePath, IContainer<User> users, IContainer<Product> products)
			throws Exception {
		IContainer<Contract> contracts = null;
		try {
			String fileAll = fRead.readFile(filePath); // read file
			if (!fileAll.isBlank()) // if not blank
				contracts = parser.parseContracts(fileAll, this.creator, users, products); // parse
																							// contracts
			else
				contracts = new ContractContainer();// init empty repo
		} catch (IOException e) {
			contracts = new ContractContainer();// init empty repo
		}

		return contracts;
	}

	@Override
	public void writeUsers(IContainer<User> users, String filePath) throws Exception {
		User admin = users.getByName("SYSADMIN");
		users.remove(admin);// remove admin while writing
		fWrite.writeItems(users, filePath);
		users.add(admin);
	}

	@Override
	public void writeProducts(IContainer<Product> products, String filePath) throws Exception {
		FileIOHelper.updateProductsStates(products);// update main products' states
		fWrite.writeItems(products, filePath);

	}

	@Override
	public void writeContracts(IContainer<Contract> contracts, String filePath) throws Exception {
		fWrite.writeItems(contracts, filePath);

	}
}
