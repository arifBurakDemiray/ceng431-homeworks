package user.auth;

import exception.UnauthorizedUserException;
import product.Product;
import user.User;


/**
 * 
 * This class handles authorize issues for user to move on operation he does or
 * stop operation because of user not authorized to do
 *
 */
public interface IAuthService {

	/**
	 * This function authorizes an user to update a state of a product
	 * @param user who is employee
	 * @throws UnauthorizedUserException for not employee users
	 */
	public void authorizeUserForUpdate(User user) throws UnauthorizedUserException;

	/**
	 * This function authorizes an user to create user
	 * @param user      who does operation
	 * @param givenUser who is going to be assigned
	 * @throws UnauthorizedUserException if user not authorized
	 */
	public void authorizeUserForCreateUser(User user, User givenUser) throws UnauthorizedUserException;

	/**
	 * This function authorizes an user to create a product
	 * @param user    who is doing operation
	 * @param product what is goinge to be created
	 * @throws UnauthorizedUserException for not authorized users
	 */
	public void authorizeUserForCreateProduct(User user, Product product) throws UnauthorizedUserException;

	/**
	 * This function authorizes an user to assign a product to a user
	 * @param user      who is doing job
	 * @param givenUser who is going to be assigned
	 * @param product   what is going to be assigned
	 * @throws UnauthorizedUserException for user not authorized to do
	 */
	public void authorizeUserForAssign(User user, User givenUser, Product product) throws UnauthorizedUserException;

}
