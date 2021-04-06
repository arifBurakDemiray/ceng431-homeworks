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
		this.authController.authorizeUserForUpdate(this.user);
	}
	
	public void createUser(User givenUser,IContainer<User> users) throws UnauthorizedUserException{		
		this.authController.authorizeUserForCreateUser(this.user,givenUser);
		users.add(givenUser);
	}
	
	public void createProduct(Product product,IContainer<Product> products) throws UnauthorizedUserException{		
		this.authController.authorizeUserForCreateProduct(this.user,product);
		products.add(product);
	}
	
	public void assignProduct(User givenUser,Product product,IContainer<Contract> contracts) throws UnauthorizedUserException{		
		this.authController.authorizeUserForAssign(this.user,givenUser,product);
		Contract newAssignment = new Contract(product, givenUser);
		contracts.add(newAssignment);
	}
	
	

}
