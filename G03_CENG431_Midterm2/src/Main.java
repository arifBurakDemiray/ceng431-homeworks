
import fileio.FileIO;
import fileio.FileWrite;
import product.*;
import storage.IContainer;

public class Main {
	public static void main(String[] args) throws Exception {
		FileIO fileIO = new FileIO();
		IContainer<Product> prds = fileIO.readProducts("data\\deneme.json");
		FileWrite fw = new FileWrite();
		fw.writeItems(prds, "data\\deneme1.json");

	}

}// .getProducts()).get(0).toString() ((ArrayList<Product>)
