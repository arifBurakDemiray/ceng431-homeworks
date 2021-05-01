package fileio;

import contract.Contract;
import contract.ContractUserOutfitsLikes;
import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import storage.StringContainer;

public abstract class ContractRepository {

	private IContainer<Contract> contracts;

	protected ContractRepository(IContainer<Contract> contracts) {
		this.contracts = contracts;
	}

	public DatabaseResult saveChanges() {
		String message = "";
		try {
			BaseRepository.saveChanges();
		} catch (FileFormatException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(null, message);
	}

	@SuppressWarnings("unchecked")
	protected void add(String userName,String outfitId) {
		
		Contract contract;
		IContainer<String> likedOutfits;
		try {
			contract = contracts.getByName(userName);
		
			likedOutfits = (IContainer<String>) contract.getContracter();
			likedOutfits.add(outfitId);
		} catch (ItemNotFoundException | NotSupportedException e) {
			likedOutfits = new StringContainer();
			likedOutfits.add(outfitId);
			Contract newContract = new ContractUserOutfitsLikes(userName,likedOutfits);
			contracts.add(newContract);
		}
		
	}
	
	protected void remove(String userName,String outfitId) {

		Contract contract;
		try {
			contract = contracts.getByName(userName);
			@SuppressWarnings("unchecked")
			IContainer<String> likedOutfits = (IContainer<String>) contract.getContracter();
			likedOutfits.remove(outfitId);
		} catch (ItemNotFoundException | NotSupportedException e) {
			
		}
		
		
	}
	
	protected boolean has(String userName,String outfitId)
	{
		Contract contract;
		try {
			contract = contracts.getByName(userName);
			@SuppressWarnings("unchecked")
			IContainer<String> likedOutfits = (IContainer<String>) contract.getContracter();
			
			if(likedOutfits.getItem(outfitId) == null)
			{
				return false;
			}
		} catch (ItemNotFoundException | NotSupportedException e) {
			return false;
		}
		return true;
	}	

	

}
