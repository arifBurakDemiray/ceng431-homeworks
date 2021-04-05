
import fileio.FileIO;
import product.*;
import storage.IContainer;

public class Main {
	public static void main(String[] args) throws Exception {
		FileIO fileIO = new FileIO();
		IContainer<Product> prds = fileIO.readProducts("deneme");
		System.out.println(prds.toString());

	}

}// .getProducts()).get(0).toString() ((ArrayList<Product>)
