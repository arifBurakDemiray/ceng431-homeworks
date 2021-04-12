package fileio;

import factory.ICreatorService;
import product.Product;
import storage.IContainer;
import user.User;
import contract.Contract;
import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;

/**
 * This class holds all informations
 */
public class FileController {

	private IContainer<User> users;
	private IContainer<Product> products;
	private IContainer<Contract> productContracts;
	private IContainer<Contract> employeeContracts;
	private IFileIO fileIO;

	public FileController(ICreatorService creator) {
		this.fileIO = new FileIO(creator);
	}

	/**
	 * This function reads all entities
	 * 
	 * @throws FileFormatException
	 */
	public void readAll() throws FileFormatException {
		try {
			users = fileIO.readUsers("data\\users.json");
			products = fileIO.readProducts("data\\products.json");
			productContracts = fileIO.readContracts("data\\productcontracts.json", users, products);
			employeeContracts = fileIO.readContracts("data\\employeecontracts.json", users, products);
		} catch (Exception e) {
			throw new FileFormatException(e.getMessage());
		}
	}

	/**
	 * This function writes all entities
	 * 
	 * @throws FileFormatException
	 */
	public void writeAll() throws FileFormatException {
		try {
			fileIO.writeUsers(users, "data\\users.json");
			fileIO.writeProducts(products, "data\\products.json");
			fileIO.writeContracts(productContracts, "data\\productcontracts.json");
			fileIO.writeContracts(employeeContracts, "data\\employeecontracts.json");
		} catch (Exception e) {
			throw new FileFormatException(e.getMessage());
		}
	}
	
	//Update all products' states
	public void updateStatesOfProduct()
	{
		FileIOHelper.updateProductsStates(products);
	}

	// Add user to system
	public boolean addUser(User user) {
		return this.users.add(user);
	}

	// add product to system
	public boolean addProduct(Product product) {
		return this.products.add(product);
	}

	// get by user name
	public User getByUserName(String userName) throws ItemNotFoundException, NotSupportedException {
		return this.users.getByName(userName);
	}

	// get by product id
	public Product getByProductId(String productId) throws ItemNotFoundException, NotSupportedException {
		return this.products.getById(productId);
	}

	public IContainer<User> users() {
		return this.users;
	}

	public IContainer<Product> products() {
		return this.products;
	}

	public IContainer<Contract> productContracts() {
		return this.productContracts;
	}

	public IContainer<Contract> employeeContracts() {
		return this.employeeContracts;
	}

}
