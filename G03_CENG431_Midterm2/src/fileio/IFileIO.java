package fileio;



import contract.Contract;
import product.Product;
import storage.IContainer;
import user.User;

public interface IFileIO {
	public IContainer<Product> readProducts(String filePath) throws Exception;
	public IContainer<User> readUsers(String filePath) throws Exception;
	public IContainer<Contract> readContracts(String filePath, IContainer<User> users, IContainer<Product> products) throws Exception;
	public void writeUsers(IContainer<User> users,String filePath) throws Exception;
	public void writeProducts(IContainer<Product> products,String filePath) throws Exception;
	public void writeContracts(IContainer<Contract> contracts,String filePath) throws Exception;
}
