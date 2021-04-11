package user.auth;

import exception.UnauthorizedUserException;
import product.Product;
import user.User;

public class AuthController implements IAuthService {

	public AuthController() {

	}

	public void authorizeUserForUpdate(User user) throws UnauthorizedUserException {
		String simpleName = user.getClass().getSimpleName();
		if (!simpleName.equals("Employee"))// if user not an employee, he can not update manually
			throw new UnauthorizedUserException("You are not authorized to update a product.");
	}

	public void authorizeUserForCreateUser(User user, User givenUser) throws UnauthorizedUserException {
		String simpleNameOfUser = user.getClass().getSimpleName();
		String simpleNameOfGivenUser = givenUser.getClass().getSimpleName();
		if (simpleNameOfUser.equals("Admin") && simpleNameOfGivenUser.equals("Manager"))
			return; // if user admin he create only managers
		if (simpleNameOfUser.equals("Manager") && simpleNameOfGivenUser.equals("Employee"))
			return; // if user manager he create only employees
		throw new UnauthorizedUserException("You are not authorized to create a user.");
		// otherwise no user authorized to create a user
	}

	public void authorizeUserForCreateProduct(User user, Product product) throws UnauthorizedUserException {
		String simpleNameOfUser = user.getClass().getSimpleName();
		String simpleNameOfProduct = product.getClass().getSimpleName();
		//If user admin he can only create assembly products
		if (simpleNameOfUser.equals("Admin") && simpleNameOfProduct.equals("Part"))
			throw new UnauthorizedUserException("You are not authorized to create a part.");
		//If user employee he can not create any product
		if (simpleNameOfUser.equals("Employee"))
			throw new UnauthorizedUserException("You are not authorized to create a product.");
	}

	public void authorizeUserForAssign(User user, User givenUser, Product product) throws UnauthorizedUserException {
		String simpleNameOfUser = user.getClass().getSimpleName();
		String simpleNameOfGivenUser = givenUser.getClass().getSimpleName();
		String simpleNameOfProduct = product.getClass().getSimpleName();
		//Admins can only assign assemblies to managers
		if (simpleNameOfUser.equals("Admin") && simpleNameOfGivenUser.equals("Manager")
				&& simpleNameOfProduct.equals("Assembly"))
			return;
		//managers can only assign parts to employees
		if (simpleNameOfUser.equals("Manager") && simpleNameOfGivenUser.equals("Employee")
				&& simpleNameOfProduct.equals("Part"))
			return;
		throw new UnauthorizedUserException("You are not authorized to assign the user.");
	}


}
