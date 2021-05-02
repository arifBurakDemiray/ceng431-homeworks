package fileio;

import contract.Contract;
import contract.ContractUserOutfitsLikes;
import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import storage.IContainer;
import storage.StringContainer;

public abstract class ContractRepository {

	// Contract container which holds contracts.
	private IContainer<Contract> contracts;

	protected ContractRepository(IContainer<Contract> contracts) {
		this.contracts = contracts;
	}

	/**
	 * The function tries to write the contracts to the necessary file.
	 * 
	 * @return DatabaseResult(null, message);
	 */
	public DatabaseResult saveChanges() {
		String message = "";
		try {
			BaseRepository.saveChanges();
		} catch (FileFormatException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(null, message);
	}

	/**
	 * The function creates a new contract between user and outfits for like/dislike
	 * 
	 * @param userName
	 * @param outfitId
	 */
	@SuppressWarnings("unchecked")
	protected void add(String userName, String outfitId) {

		Contract contract;
		IContainer<String> outfitIds;
		try {
			// get contract from contract container using user name
			contract = contracts.getByName(userName);
			// get the outfit ids list of contract
			outfitIds = (IContainer<String>) contract.getContracter();
			outfitIds.add(outfitId); // add the outfit to the outfit ids list of contract
		} catch (ItemNotFoundException | NotSupportedException e) {
			// if the constructor doesn't exists, create a new id container
			// after that add the id to the list.
			outfitIds = new StringContainer();
			outfitIds.add(outfitId);

			// create a new contract between user and outfits and add contract to the
			// container
			Contract newContract = new ContractUserOutfitsLikes(userName, outfitIds);
			contracts.add(newContract);
		}

	}

	/**
	 * The function removes the contract between user and outfit for like/dislike
	 * 
	 * @param userName
	 * @param outfitId
	 */
	protected void remove(String userName, String outfitId) {

		Contract contract;
		try {
			// get contract from contract container using user name
			contract = contracts.getByName(userName);
			@SuppressWarnings("unchecked")
			// get the outfit ids list of contract
			IContainer<String> outfitIds = (IContainer<String>) contract.getContracter();
			outfitIds.remove(outfitId); // remove the outfit from the outfit ids list of contract
		} catch (ItemNotFoundException | NotSupportedException e) {

		}

	}

	/**
	 * The function controls that there exists a contract between user and outfit
	 * for like/dislike
	 * 
	 * @param userName
	 * @param outfitId
	 */
	protected boolean has(String userName, String outfitId) {
		Contract contract;
		try {
			// get contract from contract container using user name
			contract = contracts.getByName(userName);
			@SuppressWarnings("unchecked")
			// get the outfit ids list of contract
			IContainer<String> outfitIds = (IContainer<String>) contract.getContracter();

			// If gotten id exists in the list, return true, else return false.
			if (outfitIds.getItem(outfitId) == null) {
				return false;
			}
		} catch (ItemNotFoundException | NotSupportedException e) {
			return false;
		}
		return true;
	}

}
