package fileio;

import java.util.Collection;

import product.Product;

public interface IFileIO {
	public Collection<Product> readProducts(String filePath) throws Exception;
}
