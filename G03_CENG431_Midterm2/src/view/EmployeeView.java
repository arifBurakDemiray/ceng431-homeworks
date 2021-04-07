package view;

import contract.ContractController;
import factory.Creator;
import fileio.FileController;
import user.User;

public class EmployeeView extends UserView {

	public EmployeeView(User user,FileController fileController,Creator creator,ContractController contractController){
		super(user,fileController,creator,contractController);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

}
