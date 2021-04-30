package app;

import configuration.ConfigurationManager;

/**
 * This class is startup class for the program
 * 
 * @author Furkan Sahin - 250201042  <a href="https://sahinfurkan.wordpress.com/">Furkan Sahin</a>
 * @author Arif Burak Demiray - 250201022  <a href="http://www.linkedin.com/in/arifBurakDemiray">Arif Burak Demiray</a>
 */
public class Startup {

	private final ConfigurationManager manager;
	
	public Startup() {
		manager = new ConfigurationManager(); 
	}
	
	/**
	 * This function starts to run the system with usage of {@linkplain ConfigurationManager}
	 */
	public void run() {
		manager.configureServices();
	}
	
}
