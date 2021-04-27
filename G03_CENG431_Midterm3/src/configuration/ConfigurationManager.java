package configuration;


import exception.FileFormatException;
import fileio.BaseRepository;


public class ConfigurationManager {
	
	private BaseRepository baseRepository;
	
	public ConfigurationManager(){}

	private void addRepositories() throws FileFormatException{
		baseRepository = new BaseRepository();
		baseRepository.initDatabase();
	}

	public void configureServices(){
		try {
			addRepositories();
		} catch (FileFormatException e) {
			System.err.println(e.getMessage());
		}
	}

}
