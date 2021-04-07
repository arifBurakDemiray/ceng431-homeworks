package view;

import contract.ContractController;
import factory.Creator;
import fileio.FileController;
import user.Admin;
import user.Employee;
import user.Manager;
import user.User;
import user.UserController;

public class UserView extends View{
	
	protected UserController userController;
	protected ContractController contractController;
	private User user;
	public UserView(User user,FileController fileController,Creator creator) {
		super(fileController,creator);
		this.user = user;
		this.userController = new UserController(user);
		this.contractController = new ContractController(fileController.contracts());
	}
	
	protected UserView(User user,FileController fileController,Creator creator,ContractController contractController){
		super(fileController,creator);
		this.user = user;
		this.userController = new UserController(user);
		this.contractController = contractController;
	}
	
	public User getUser() {
		return user;
	}


	public void navigate() {
		if (this.user instanceof Admin) {
			(new AdminView(user,fileController,creator,contractController)).start();
		} else if (this.user instanceof Manager) {
			(new ManagerView(user,fileController,creator,contractController)).start();
		} else if (this.user instanceof Employee) {
			(new EmployeeView(user,fileController,creator,contractController)).start();
		}
	}

	@Override
	public void start() {
		navigate();
		
	}
	
}
