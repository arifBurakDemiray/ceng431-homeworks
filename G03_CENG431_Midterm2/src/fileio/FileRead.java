package fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {

	protected FileRead() {
	}

	/**
	 * This function reads a file
	 * 
	 * @param filename of the file
	 * @returns read file in string format
	 * @throws IOException
	 */
	protected String readFile(String filename) throws IOException {

		File file = new File(filename); // opening file
		BufferedReader br;
		String fileAll = "";
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				fileAll += line;// add all lines to the string
			}
			br.close();// close file
		} catch (IOException e) {
			throw new IOException("Could not be read " + filename);
		}
		return fileAll;

	}
}
