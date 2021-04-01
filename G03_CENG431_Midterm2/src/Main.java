import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import state.ProductState;
import org.json.JSONArray;

public class Main {
	public static void main(String[] args)
	{
		ProductState st = new ProductState();
		System.out.println(st.getState());
		
		File file = new File("deneme.json"); // opening file
		BufferedWriter br;
		try {
			br = new BufferedWriter(new FileWriter(file));
					br.append("\nTEAMSTECH\n");// append it - TEAMSTECH indicates file is read at least one time.
		br.close();// and close
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // setting up buffered writer in write mode to		
		JSONArray js = new JSONArray();
	}
}


		