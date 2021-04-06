package auth;

import exception.UnauthorizedUserException;
import product.Product;
import user.User;

public class AuthController {
	
	
	public AuthController()
	{
		
	}
	
	public void authorizeUserForUpdate(User user) throws UnauthorizedUserException{
		String simpleName = user.getClass().getSimpleName();
		if(!simpleName.equals("Employee"))
			throw new UnauthorizedUserException("You are not authorized to update a product.");
	}
	
	public void authorizeUserForCreateUser(User user, User givenUser ) throws UnauthorizedUserException{
		String simpleNameOfUser = user.getClass().getSimpleName();
		String simpleNameOfGivenUser = givenUser.getClass().getSimpleName();
		if(simpleNameOfUser.equals("Admin") && simpleNameOfGivenUser.equals("Manager"))
			return;
		if(simpleNameOfUser.equals("Manager") && simpleNameOfGivenUser.equals("Employee"))
			return;
		throw new UnauthorizedUserException("You are not authorized to create a user.");
	}
	
	public void authorizeUserForCreateProduct(User user, Product product ) throws UnauthorizedUserException{
		String simpleNameOfUser = user.getClass().getSimpleName();
		String simpleNameOfProduct = product.getClass().getSimpleName();
		if(simpleNameOfUser.equals("Admin") && simpleNameOfProduct.equals("Part"))
			throw new UnauthorizedUserException("You are not authorized to create a part.");
		if(simpleNameOfUser.equals("Employee"))
			throw new UnauthorizedUserException("You are not authorized to create a product.");
	}
	
	public void authorizeUserForAssign(User user, User givenUser, Product product) throws UnauthorizedUserException{
		String simpleNameOfUser = user.getClass().getSimpleName();
		String simpleNameOfGivenUser = givenUser.getClass().getSimpleName();
		String simpleNameOfProduct = product.getClass().getSimpleName();
		if(simpleNameOfUser.equals("Admin") && simpleNameOfGivenUser.equals("Manager") && simpleNameOfProduct.equals("Assembly"))
			return;
		if(simpleNameOfUser.equals("Manager") && simpleNameOfGivenUser.equals("Employee") && simpleNameOfProduct.equals("Employee"))
			return;
		throw new UnauthorizedUserException("You are not authorized to assign the user.");
	}

}
