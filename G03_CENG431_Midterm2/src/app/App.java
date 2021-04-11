package app;

import exception.UnauthorizedUserException;
import view.AppView;
import view.View;

/**
 * @author Furkan Sahin - 250201042
 * @author Arif Burak Demiray - 250201022
 */
public class App {
	public static void main(String[] args) {
		View view = new AppView(); // init app view
		try { // this try because of view's function definition, AppView not throws any
				// exception normally
			view.start(); // start views
		} catch (UnauthorizedUserException e) {
		}
	}
}
