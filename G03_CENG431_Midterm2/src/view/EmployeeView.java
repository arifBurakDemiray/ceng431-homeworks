package view;

import contract.ContractController;
import exception.ItemNotFoundException;
import exception.UnauthorizedUserException;
import factory.Creator;
import fileio.FileController;
import product.Product;
import user.User;

public class EmployeeView extends UserView {

	


	protected EmployeeView(User user, FileController fileController, Creator creator,
			ContractController contractControllerProduct) {
		super(user, fileController, creator, contractControllerProduct);
	}

	@Override
	public void start() throws UnauthorizedUserException {
		menu();
		
	}
	
	private void changeProductStatus() throws UnauthorizedUserException
	{		
		try {
			Product prd = (Product) contractControllerProduct.getContracterOfContractee(this.getUser().getUserName());
			if(!prd.getProductState().equals("Completed"))
			{
				System.out.println("State is updated");
				userController.updateProduct(prd);
			}
			else {
				System.out.println("This product is already completed.");
			}
		} catch (ItemNotFoundException e) {
			System.out.println(e.getMessage());
		} 				
	}
	
	private void printPart()
	{		
		try {
			Product prd = (Product) contractControllerProduct.getContracterOfContractee(this.getUser().getUserName());
			System.out.println("\nTitle: "+prd.getTitle()+"\nId: "+prd.getId()+"\nState: "+prd.getProductState()+"\n");
		} catch (ItemNotFoundException e) {
			System.out.println(e.getMessage());
		} 				
	}
	
	
	protected void menu() throws UnauthorizedUserException{
		String menuString = "1: Change Product Status\n2: Print Part\n3: Print Menu\n4: Logout";
		System.out
				.println("\n\tEMPLOYEE MENU\n\n"+menuString);
		String menuChoice = "";
		while (!menuChoice.equals("4")) {
			menuChoice = inputReceiver.getString("\nChoice : ");
			switch (menuChoice) {
			case "1": {
				changeProductStatus();
				break;
			}
			case "2": {
				printPart();
				break;
			}
			case "3": {
				System.out.println(menuString);
				break;
			}
			case "4":
				super.logout();
				break;
			default:
				System.out.println("Write a valid number.");
			}
		}
	}

}
