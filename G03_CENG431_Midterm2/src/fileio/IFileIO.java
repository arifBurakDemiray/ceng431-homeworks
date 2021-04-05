package fileio;



import product.Product;
import storage.IContainer;
import user.User;

public interface IFileIO {
	public IContainer<Product> readProducts(String filePath) throws Exception;
	public IContainer<User> readUser(String filePath) throws Exception;
}
