package view;

import exception.UnauthorizedUserException;
import factory.Creator;
import fileio.FileController;

public abstract class View {

	protected InputReceiver inputReceiver;
	protected Creator creator;
	protected FileController fileController;
	public View(){
		this.inputReceiver = new InputReceiver();
		creator = new Creator();
		fileController = new FileController(creator);
	}	
	
	protected View(FileController fController, Creator crt){
		fileController = fController;
		creator = crt;
		this.inputReceiver = new InputReceiver();
	}

	public abstract void start() throws UnauthorizedUserException;
	protected abstract void menu() throws UnauthorizedUserException;
}
