package fileio;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import storage.IContainer;
import java.io.IOException;
import java.util.Iterator;

public class FileWrite {

	public FileWrite() {
	}

	/**
	 * This function is protected because we provide a IFileIO interface to
	 * communicate with read write request. So we hid them.
	 * 
	 * @param <T>      object type which is going to be wrote to the file
	 * @param items    objects holder
	 * @param filePath of the object file
	 * @param header   of the file
	 * @returns true if succeeded, false if there is problem with the file and
	 *          container is empty
	 */
	protected <T> boolean writeItems(IContainer<T> items, String filePath, String header) {

		if (items.isEmpty()) {// if there is no item in the list returns false
			System.out.println("The container is empty, please do not use empty container");
			return false;
		}
		try {// for catching IOException
			File file = new File(filePath); // opening file
			BufferedWriter br = new BufferedWriter(new FileWriter(file)); // setting up buffered writer in write mode to
																			// clean given file

			Iterator<T> iterator = items.iterator(); // taking iterator of the IContainer
			T item = iterator.next(); // and taking first item

			br.write(header + "\n"); // write header and clean file
			while (iterator.hasNext()) {// loop in container and look for the next
				String data = item.toString(); // convert items to string
				br.append(data + "\n");// append it to the file and add a newline
				item = iterator.next();// move on to the next

			}
			String data = item.toString();// last one
			br.append(data + "\nTEAMSTECH\n");// append it - TEAMSTECH indicates file is read at least one time.
			br.close();// and close
		} catch (IOException e) {// IO exception
			System.out.println("There is a problem with the file\nplease check before write operation");
			return false;
		}

		return true;
	}

}
