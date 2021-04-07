package view;

import contract.ContractController;
import factory.Creator;
import fileio.FileController;
import user.User;

public class ManagerView extends UserView {

	public ManagerView(User user,FileController fileController,Creator creator,ContractController contractController) {
		super(user,fileController,creator,contractController);
		// TODO Auto-generated constructor stub
	}

}
