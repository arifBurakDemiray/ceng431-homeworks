package configuration;


import exception.FileFormatException;
import fileio.BaseRepository;
import view.AppWindow;


/**
 * 
 * This class configures services of the program 
 *
 */
public class ConfigurationManager {
	
	private BaseRepository baseRepository;
	
	/**
	 * Constructor
	 */
	public ConfigurationManager(){}

	/**
	 * this function initialises data of the program for function to use {@link #configureServices()}
	 * @throws FileFormatException if there is  a problem with file , gives error
	 */
	private void addRepositories() throws FileFormatException{
		baseRepository = new BaseRepository();
		baseRepository.initDatabase();
	}

	/**
	 * This function initialises app window for ui for function to use {@link #configureServices()}
	 */
	private void createAppWindow(){
		new AppWindow();

	}
	
	/**
	 * This function configures data and ui services of the system 
	 */
	public void configureServices(){
		try {
			addRepositories();
			createAppWindow();
		} catch (FileFormatException | RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

}
