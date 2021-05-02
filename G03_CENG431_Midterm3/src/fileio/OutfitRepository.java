package fileio;

import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Outfit;
import storage.IContainer;

public class OutfitRepository {

	public OutfitRepository() {

	}

	/**
	 * The function tries to write the outfits to the necessary file.
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
	 * The function tries to find the outfit of given id and returns the database
	 * result. If outfit is found > database.result object = outfit, else
	 * database.result object = null
	 * 
	 * @param id = gotten outfit id
	 * @return database result
	 */
	public DatabaseResult getOutfitById(String id) {
		// get outfit container of system which holds all outfits
		final IContainer<Outfit> outfits = BaseRepository.outfits();
		String message = "";
		Object result = null;
		try {
			// try to find the outfit
			result = outfits.getById(id);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	/**
	 * The function tries to find the outfit of given type and returns the database
	 * result. If outfit type found > database.result object = outfit else
	 * database.result object = null
	 * 
	 * @param type = gotten outfit type
	 * @return database result
	 */
	public DatabaseResult getOutfitByType(String type) {
		// get outfit container of system which holds all outfits
		final IContainer<Outfit> outfits = BaseRepository.outfits();
		String message = "";
		Object result = null;
		try {
			// try to find the outfit
			result = outfits.getByName(type);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	/**
	 * The function returns the outfit container of system
	 * 
	 * @return Outfit Container which holds all outfits
	 */
	public final IContainer<Outfit> getOutfits() {
		return BaseRepository.outfits();
	}

}
