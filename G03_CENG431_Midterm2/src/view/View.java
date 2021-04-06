package view;

import auth.Login;
import exception.FileFormatException;
import factory.Creator;
import fileio.FileController;

public class View {

	private Creator creator;
	private FileController fileController;
	private InputReceiver inputReceiver;
	
	public View() {
		creator = new Creator();
		fileController = new FileController(creator);
		inputReceiver = new InputReceiver();
	}
	
	public void init() throws FileFormatException {
		fileController.readAll();

		
	}
	
	public void login() throws Exception {
		String userName = inputReceiver.getUsername();
		String password = inputReceiver.getPassword();
		Login login = new Login();
		login.login(userName, password, fileController.users());
	}
	
	public void start() {
		try {
			init();
			login();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
