package view;

import auth.Login;
import exception.FileFormatException;
import factory.Creator;
import fileio.FileController;
import user.User;

public class AppView extends View {

	private User user;

	public AppView(FileController fileController,Creator creator) {
		super(fileController,creator);
	}

	public void init() throws FileFormatException {
		fileController.readAll();
	}

	public void login() throws Exception {
		String userName = this.inputReceiver.getString("Username : ");
		String password = this.inputReceiver.getString("Password : ");
		Login login = new Login();
		this.user = login.login(userName, password, fileController.users());
	}

	public void start() {
		try {
			init();
			login();
			UserView userView = new UserView(this.user,fileController,creator);
			userView.navigate();
			save();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void save() throws FileFormatException{
		fileController.writeAll();
	}

}
