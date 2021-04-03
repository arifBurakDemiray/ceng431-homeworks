package fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {

	public FileRead(){}
	
	public String readFile(String filename) throws IOException{
		
		File file = new File("data\\deneme.json"); // opening file
		BufferedReader br;
		String fileAll = "";
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				fileAll += line;
			}
			br.close();
		} catch (IOException e) {
			throw new IOException("Could not be read "+filename);
		}
		return fileAll;
		
	}
}
