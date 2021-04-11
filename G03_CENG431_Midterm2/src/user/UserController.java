package user;

import contract.Contract;
import contract.ContractController;
import contract.ContractUserProduct;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import exception.UnauthorizedUserException;
import factory.CreationResult;
import factory.ICreatorService;
import fileio.FileController;
import product.Assembly;
import product.Product;
import user.auth.AuthController;
import user.auth.IAuthService;

public class UserController {

	private User user;
	private IAuthService authService; // AuthController object to authenticate user for processes

	/**
	 * The constructor for UserController
	 * 
	 * @param user = given user object.
	 */
	public UserController(User user) {
		this.user = user;
		this.authService = new AuthController();
	}

	/**
	 * The function tries to update the state of given product if loggedInUser is
	 * authorised.
	 * 
	 * @param product = given product
	 * @throws UnauthorizedUserException if loggedInUser is not authorised for the
	 *                                   process, throw UnauthorizedUserException
	 */
	public void updateProduct(Product product) throws UnauthorizedUserException {

		// try to authorise loggedInUser for process or throw an exception
		authService.authorizeUserForUpdate(this.user);
		// if loggedInUser is authorised, update the state of given product

		product.updateState();
	}

	/**
	 * The function tries to create a new user if loggedInUser is authorised.
	 * 
	 * @param givenUser   = created user object
	 * @param fController = fileController which holds containers ( files data )
	 * @throws UnauthorizedUserException if loggedInUser is not authorised for the
	 *                                   process, throw UnauthorizedUserException
	 */
	public void createUser(User givenUser, FileController fController) throws UnauthorizedUserException {

		// try to authorise loggedInUser for process or throw an exception
		authService.authorizeUserForCreateUser(this.user, givenUser);
		// if loggedInUser is authorised, add the created user to the userContainer in
		// the fController invoking fController.addUser()

		fController.addUser(givenUser);
	}

	/**
	 * The function tries to create a new product if loggedInUser is authorised.
	 * 
	 * @param product     = created product object
	 * @param fController = fileController which holds containers ( files data )
	 * @throws UnauthorizedUserException if loggedInUser is not authorised for the
	 *                                   process, throw UnauthorizedUserException
	 */
	public void createProduct(Product product, FileController fController) throws UnauthorizedUserException {

		// try to authorise loggedInUser for process or throw an exception
		authService.authorizeUserForCreateProduct(this.user, product);
		// if loggedInUser is authorised, add the created product to the
		// productContainer in the fController invoking fController.addProduct()

		fController.addProduct(product);
	}

	/**
	 * The function tries to assign a product to given user if loggedInUser is
	 * authorised.
	 * 
	 * @param product     = product object to assign
	 * @param givenUser   = given user which is wanted to hold the product.
	 * @param fController = fileController which holds containers ( files data )
	 * @throws UnauthorizedUserException if loggedInUser is not authorised for the
	 *                                   process, throw UnauthorizedUserException
	 */
	public void assignProduct(User givenUser, Product product, FileController fController,
			ContractController contractControllerProduct) throws UnauthorizedUserException {

		// try to authorise loggedInUser for process or throw an exception
		authService.authorizeUserForAssign(this.user, givenUser, product);

		try {
			// Control that given user has already a product or not. If user has no product
			// catch the exception and try to assign product
			contractControllerProduct.getContracterOfContractee(givenUser.getUserName());
			System.out.println("User has already product.");
		} catch (ItemNotFoundException e) {
			try {
				// Control that given product has already a user or not. If product has no user
				// catch the exception assign
				contractControllerProduct.getContracteeOfContracter(product.getId());
				System.out.println("Product has already user.");
			} catch (ItemNotFoundException e1) {

				// Create new contract between given user and given product
				Contract newAssignment = new ContractUserProduct(givenUser, product);
				// Add the new contract to the productscontract
				contractControllerProduct.addContract(newAssignment);
				System.out.println(product.getTitle() + " is assigned to " + givenUser.getUserName());
			} catch (NotSupportedException e2) {
				System.out.println(e2.getMessage());
			}

		}
	}

	
	/**
	 * The functions is to create a new assembly or part for manager's product and
	 * insert the created product to given assembly
	 * 
	 * @param fileController = fileController which holds containers ( files data )
	 * @param creator        = creator to create any type of instance of
	 * @param productId      = product id which the created product is inserted into
	 *                       it
	 * @param productType    = gotten product type input from manager / assembly or
	 *                       part /
	 * @param productTitle   = gotten product title input from manager
	 */
	public void createProductForManager(FileController fileController, ICreatorService creator, String productId,
			String productType, String productTitle) {

		try {
			// Try to find selected product which the created product is inserted into it
			Product selectedUpperProduct = (Product) fileController.getByProductId(productId);

			// Control selected product is an assembly, if not throw an
			// NotSupportedException
			selectProduct(selectedUpperProduct);

			// Try to create a product using creator
			// If creation is not successful, print error message
			CreationResult cr = creator.createProduct(productType, productTitle, null, null);
			if (cr.object == null) {
				System.out.println(cr.message);
			} else {

				// If creation is successful add the created product(assembly or part) inside to
				// selected assembly
				// in user is authorised and to add the created user to fileController
				Product createdProduct = (Product) cr.object;
				((Assembly) selectedUpperProduct).addProduct(createdProduct);
				System.out.println("Product : " + productTitle + " ( " + productType + " ) is produced.");
			}
		} catch (ItemNotFoundException | NotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Invoked by createProductForManager() The function controls given product is
	 * assembly or not
	 * 
	 * @param product = given product
	 * @throws NotSupportedException
	 */
	private void selectProduct(Product product) throws NotSupportedException {
		String managerClass = this.user.getClass().getSimpleName();
		String productClass = product.getClass().getSimpleName();
		if (managerClass.equals("Manager") && productClass.equals("Assembly"))
			return;
		throw new NotSupportedException("You cannot create a product inside a part.");
	}

}
