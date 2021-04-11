package view;

import exception.UnauthorizedUserException;
import factory.Creator;
import factory.ICreatorService;
import fileio.FileController;

public abstract class View {

	protected InputReceiver inputReceiver; // it get input from user
	protected ICreatorService creator; // creator to create any type of instance of class
	protected FileController fileController; // holds containers ( file data )

	/**
	 * The constructor for View with default variables
	 */
	public View() {
		this.inputReceiver = new InputReceiver();
		creator = new Creator();
		fileController = new FileController(creator);
	}

	/**
	 * The constructor for View with given file controller and creator
	 * 
	 * @param fController
	 * @param crt
	 */
	protected View(FileController fController, ICreatorService crt) {
		fileController = fController;
		creator = crt;
		this.inputReceiver = new InputReceiver();
	}

	/**
	 * The function initialise the processes of the view
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	public abstract void start() throws UnauthorizedUserException;

	/**
	 * The function prints menu and get a menu choice from user
	 * 
	 * @throws UnauthorizedUserException if user is not authorised for a process
	 */
	protected abstract void menu() throws UnauthorizedUserException;
}
