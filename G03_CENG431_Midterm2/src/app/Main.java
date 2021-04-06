package app;

import contract.Contract;
import fileio.FileIO;
import fileio.FileWrite;
import product.*;
import storage.IContainer;
import user.User;

public class Main {
	public static void main(String[] args) throws Exception {
		FileIO fileIO = new FileIO();
		IContainer<Product> prds = fileIO.readProducts("data\\deneme.json");
		IContainer<User> users = fileIO.readUsers("data\\deneme2.json");
		IContainer<Contract> ct = fileIO.readContracts("data\\deneme1.json", users, prds);
		FileWrite fw = new FileWrite();
		fw.writeItems(prds, "data\\products.json");
	}

}// .getProducts()).get(0).toString() ((ArrayList<Product>)
