package user;

import auth.AuthController;
import contract.Contract;
import contract.ContractController;
import contract.ContractUserProduct;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserException;
import factory.CreationResult;
import factory.Creator;
import fileio.FileController;
import product.Assembly;
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
		fController.addUser(givenUser);
	}

	public void createProduct(Product product, FileController fController) throws UnauthorizedUserException {
		authController.authorizeUserForCreateProduct(this.user, product);
		fController.addProduct(product);
	}

	public void assignProduct(User givenUser, Product product, FileController fController,
			ContractController contractControllerProduct) throws UnauthorizedUserException {
		authController.authorizeUserForAssign(this.user, givenUser, product);
		try {
			contractControllerProduct.getContracterOfContractee(givenUser.getUserName());
			System.out.println("User has already product.");
		} catch (ItemNotFoundException e) {
			try {
				contractControllerProduct.getContracteeOfContracter(product.getId());
				System.out.println("Product has already user.");
			} catch (ItemNotFoundException e1) {
				Contract newAssignment = new ContractUserProduct(givenUser, product);
				contractControllerProduct.addContract(newAssignment);
				System.out.println(product.getTitle() + " is assigned to " + givenUser.getUserName());
			} catch (NotSupportedException e2) {
				System.out.println(e2.getMessage());
			}

		}
	}

	public void assignEmployee(User employee, FileController fController, IContainer<User> employeesOfManager)
			throws UnauthorizedUserException {
		authController.authorizeUserForAssingEmployee(this.user, employee);
		employeesOfManager.add(employee);
	}
	
	public void createProductForManager(FileController fileController,Creator creator,  String productId, String productType,String productTitle)
	{
		try {
			Product selectedUpperProduct = (Product) fileController.getByProductId(productId);
			selectProduct(selectedUpperProduct);

			CreationResult cr = creator.createProduct(productType, productTitle, null, null);
			if (cr.object == null) {
				System.out.println(cr.message);
			} else {
				Product createdProduct = (Product) cr.object;
				((Assembly) selectedUpperProduct).addProduct(createdProduct);
				System.out.println("Product : " + productTitle + " ( " + productType + " ) is produced.");
			}
		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void selectProduct(Product product) throws NotSupportedException {
			String managerClass = this.user.getClass().getSimpleName();
			String productClass = product.getClass().getSimpleName();
			if(managerClass.equals("Manager") && productClass.equals("Assembly"))
				return;
			throw new NotSupportedException("You cannot create a product inside a part.");
	}

}
