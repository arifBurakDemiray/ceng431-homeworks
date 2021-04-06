package app;


import contract.Contract;
import contract.ContractController;
import factory.Creator;
import fileio.FileIO;
import fileio.FileWrite;
import product.*;
import storage.IContainer;
import user.User;

public class Main {
	public static void main(String[] args) throws Exception {
		Creator creator = new Creator();
		FileIO fileIO = new FileIO(creator);
		IContainer<Product> prds = fileIO.readProducts("data\\deneme.json");
		IContainer<User> users = fileIO.readUsers("data\\deneme2.json");
		IContainer<Contract> ct = fileIO.readContracts("data\\deneme1.json", users, prds);
		ContractController contractController = new ContractController(ct);
		FileWrite fw = new FileWrite();		
		fw.writeItems(prds, "data\\products.json");
	}

}
