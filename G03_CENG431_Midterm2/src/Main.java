
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import fileio.FileIO;
import product.*;
public class Main {
	public static void main(String[] args) throws Exception  {
		FileIO fileIO = new FileIO();
		try {
			ArrayList<Product> prds = (ArrayList<Product>) fileIO.readProducts("deneme");
			System.out.println( ((Assembly)prds.get(0)).toString());
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}//.getProducts()).get(0).toString() ((ArrayList<Product>) 
