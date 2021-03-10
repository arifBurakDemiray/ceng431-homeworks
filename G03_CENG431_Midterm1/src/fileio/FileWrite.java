package fileio;

import user.User;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import storage.IContainer;
import team.Team;
import java.io.IOException;
import java.util.Iterator;

public class FileWrite<T> {

	public FileWrite(){}

	public boolean writeUsers(IContainer<T> items, String filePath) {
		//User Type,User Name,User ID,Email,Password,Team ID,
		File file = new File(filePath);
		try{
		BufferedWriter br = new BufferedWriter(new FileWriter(file));
		Iterator<T> iterator = items.getIterator();
		T item = iterator.next();
	
		br.write("User Type,User Name,User ID,Email,Password,Team ID,\n");
		while(iterator.hasNext()){
			String data = item.toString();
			br.append(data+"\n");
			item = iterator.next();
		
		}
		String data = item.toString();
		br.append(data+"\n");
		br.close();
		}
		catch(IOException e){
			System.out.println("There is a problem with the file\nplease check before write operation");
			return false;
		}

		
		
		return true;
	}
	
	public boolean writeTeams(IContainer<Team> teams){
		return false;
	}
		
		
}
