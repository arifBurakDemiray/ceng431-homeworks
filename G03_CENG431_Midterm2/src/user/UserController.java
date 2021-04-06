package user;

import auth.AuthController;
import contract.Contract;
import exception.UnauthorizedUserException;
import product.Product;
import storage.IContainer;

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
	
	public void createUser(User givenUser,IContainer<User> users) throws UnauthorizedUserException{		
		authController.authorizeUserForCreateUser(this.user,givenUser);
		users.add(givenUser);
	}
	
	public void createProduct(Product product,IContainer<Product> products) throws UnauthorizedUserException{		
		authController.authorizeUserForCreateProduct(this.user,product);
		products.add(product);
	}
	
	public void assignProduct(User givenUser,Product product,IContainer<Contract> contracts) throws UnauthorizedUserException{		
		authController.authorizeUserForAssign(this.user,givenUser,product);
		Contract newAssignment = new Contract(product, givenUser);
		contracts.add(newAssignment);
	}
	
	

}
