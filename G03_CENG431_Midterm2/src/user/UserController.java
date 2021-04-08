package user;

import auth.AuthController;
import contract.Contract;
import contract.ContractController;
import contract.ContractControllerProduct;
import contract.ContractManagerEmployee;
import contract.ContractUserProduct;
import exception.ItemNotFoundException;
import exception.UnauthorizedUserException;
import fileio.FileController;
import product.Product;
import storage.IContainer;

public class UserController {

	private User user;
	private AuthController authController;

	public UserController(User user) {
		this.user = user;
		this.authController = new AuthController();
	}

	public void updateProduct(Product product) throws UnauthorizedUserException {
		authController.authorizeUserForUpdate(this.user);
		product.updateState();
	}

	public void createUser(User givenUser, FileController fController) throws UnauthorizedUserException {
		authController.authorizeUserForCreateUser(this.user, givenUser);
		fController.users().add(givenUser);
	}

	public void createProduct(Product product, FileController fController) throws UnauthorizedUserException {
		authController.authorizeUserForCreateProduct(this.user, product);
		fController.products().add(product);
	}

	public void assignProduct(User givenUser, Product product, FileController fController,
			ContractController contractControllerProduct) throws UnauthorizedUserException {
		authController.authorizeUserForAssign(this.user, givenUser, product);
		try {
			((ContractControllerProduct) contractControllerProduct).getContracterOfContractee(givenUser.getUserName());
			System.out.println("User has already product.");
		} catch (ItemNotFoundException e) {
			try {
				((ContractControllerProduct) contractControllerProduct).getContracteeOfContracter(product.getId());
				System.out.println("Product has already user.");
			} catch (ItemNotFoundException e1) {
				Contract newAssignment = new ContractUserProduct(givenUser, product);
				fController.productContracts().add(newAssignment);
				System.out.println(product.getTitle() + " is assigned to " + givenUser.getUserName());
			}

		}
	}

	public void assignEmployee(User employee, FileController fController, IContainer<User> employeesOfManager)
			throws UnauthorizedUserException {
		authController.authorizeUserForAssingEmployee(this.user, employee);
		employeesOfManager.add(employee);
	}

}
