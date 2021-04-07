package user;

import auth.AuthController;
import contract.Contract;
import exception.UnauthorizedUserException;
import fileio.FileController;
import product.Product;
public class UserController {
	
	
	private User user;
	private AuthController authController;
	public UserController(User user)
	{
		this.user = user;
		this.authController = new AuthController();
	}
	
	public void updateProduct() throws UnauthorizedUserException{		
		authController.authorizeUserForUpdate(this.user);
	}
	
	public void createUser(User givenUser,FileController fController) throws UnauthorizedUserException{		
		authController.authorizeUserForCreateUser(this.user,givenUser);
		fController.users().add(givenUser);
	}
	
	public void createProduct(Product product,FileController fController) throws UnauthorizedUserException{		
		authController.authorizeUserForCreateProduct(this.user,product);
		fController.products().add(product);
	}
	
	public void assignProduct(User givenUser,Product product,FileController fController) throws UnauthorizedUserException{		
		authController.authorizeUserForAssign(this.user,givenUser,product);
		Contract newAssignment = new Contract(product, givenUser);
		fController.contracts().add(newAssignment);
	}
	
	

}
