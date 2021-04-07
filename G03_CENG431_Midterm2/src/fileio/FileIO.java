package fileio;


import contract.Contract;
import factory.Creator;
import fileio.parser.ContractParser;
import fileio.parser.ProductParser;
import fileio.parser.UserParser;
import product.Product;
import storage.IContainer;
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
		String fileAll = fRead.readFile(filePath);
		return (new ProductParser()).parseProducts(fileAll,this.creator);
	}

	@Override
	public IContainer<User> readUsers(String filePath) throws Exception {
		String fileAll = fRead.readFile(filePath);
		IContainer<User> users =  (new UserParser()).parseUsers(fileAll, this.creator);
		users.add(new Admin("SYSADMIN","SYSADMIN"));
		return users;
	}

	@Override
	public IContainer<Contract> readContracts(String filePath,IContainer<User> users, IContainer<Product> products) throws Exception {
		String fileAll = fRead.readFile(filePath);
		return (new ContractParser()).parseContracts(fileAll, this.creator, users, products);
	}

	@Override
	public void writeUsers(IContainer<User> users, String filePath) throws Exception {
		User admin = users.getByName("SYSADMIN");
		users.remove(admin);
		fWrite.writeItems(users, filePath);
		
	}

	@Override
	public void writeProducts(IContainer<Product> products, String filePath) throws Exception {
		fWrite.writeItems(products, filePath);
		
	}

	@Override
	public void writeContracts(IContainer<Contract> contracts, String filePath) throws Exception {
		fWrite.writeItems(contracts, filePath);
		
	}
}
