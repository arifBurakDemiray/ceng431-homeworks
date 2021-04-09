package fileio;

import java.io.IOException;

import contract.Contract;
import factory.Creator;
import fileio.parser.ContractParser;
import fileio.parser.ProductParser;
import fileio.parser.UserParser;
import product.Product;
import storage.ContractContainer;
import storage.IContainer;
import storage.ProductContainer;
import storage.UserContainer;
import user.Admin;
import user.User;

public class FileIO implements IFileIO {
	private FileRead fRead;
	private Creator creator;
	private FileWrite fWrite;

	public FileIO(Creator creator) {
		this.fRead = new FileRead(); // initialise file read
		this.creator = creator;
		this.fWrite = new FileWrite(); // initialise file write
	}

	@Override
	public IContainer<Product> readProducts(String filePath) throws Exception {
		IContainer<Product> products = null;
		try {
			String fileAll = fRead.readFile(filePath);
			if (!fileAll.isBlank())
				products = (new ProductParser()).parseProducts(fileAll, this.creator);
			else
				products = new ProductContainer();
		} catch (IOException e) {
			products = new ProductContainer();
		}

		return products;
	}

	@Override
	public IContainer<User> readUsers(String filePath) throws Exception {
		IContainer<User> users = null;
		try {
			String fileAll = fRead.readFile(filePath);
			if (!fileAll.isBlank())
				users = (new UserParser()).parseUsers(fileAll, this.creator);
			else
				users = new UserContainer();
		} catch (IOException e) {
			users = new UserContainer();
		}

		users.add(new Admin("SYSADMIN", "SYSADMIN"));
		return users;
	}

	@Override
	public IContainer<Contract> readContracts(String filePath, IContainer<User> users, IContainer<Product> products)
			throws Exception {
		IContainer<Contract> contracts = null;
		try {
			String fileAll = fRead.readFile(filePath);
			if (!fileAll.isBlank())
				contracts = (new ContractParser()).parseContracts(fileAll, this.creator, users, products);
			else
				contracts = new ContractContainer();
		} catch (IOException e) {
			contracts = new ContractContainer();
		}

		return contracts;
	}

	@Override
	public void writeUsers(IContainer<User> users, String filePath) throws Exception {
		User admin = users.getByName("SYSADMIN");
		users.remove(admin);
		fWrite.writeItems(users, filePath);
		users.add(admin);
	}

	@Override
	public void writeProducts(IContainer<Product> products, String filePath) throws Exception {
		FileIOHelper.updateProductsStates(products);
		fWrite.writeItems(products, filePath);

	}

	@Override
	public void writeContracts(IContainer<Contract> contracts, String filePath) throws Exception {
		fWrite.writeItems(contracts, filePath);

	}
}
