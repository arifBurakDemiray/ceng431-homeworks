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

	protected <T> boolean writeItems(IContainer<T> items, String filePath, String header) {
		File file = new File(filePath);
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			Iterator<T> iterator = items.iterator();
			T item = iterator.next();

			br.write(header + "\n");
			while (iterator.hasNext()) {
				String data = item.toString();
				br.append(data + "\n");
				item = iterator.next();

			}
			String data = item.toString();
			br.append(data + "\n");
			br.close();
		} catch (IOException e) {
			System.out.println("There is a problem with the file\nplease check before write operation");
			return false;
		}

		return true;
	}

}
