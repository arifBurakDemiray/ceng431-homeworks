
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import fileio.FileIO;
import product.*;
public class Main {
	public static void main(String[] args)  {
		/*FileIO fileIO = new FileIO();
		try {
			ArrayList<Product> prds = (ArrayList<Product>) fileIO.readProducts("deneme");
			System.out.println( ((ArrayList<Product>) ((Assembly)prds.get(0)).getProducts()).get(0).toString());
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Product prd = new Assembly("45","onNuma");
		System.out.println(prd.toString());

	}


}
