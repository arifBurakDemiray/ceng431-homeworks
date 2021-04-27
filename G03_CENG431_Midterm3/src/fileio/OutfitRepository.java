package fileio;

import exception.FileFormatException;
import exception.ItemNotFoundException;
import exception.NotSupportedException;
import model.Outfit;
import storage.IContainer;

public class OutfitRepository {

	public OutfitRepository() {

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

	public DatabaseResult getOutfitById(String id) {
		final IContainer<Outfit> outfits = BaseRepository.outfits();
		String message = "";
		Object result = null;
		try {
			result = outfits.getById(id);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}

	public DatabaseResult getOutfitByType(String type) {
		final IContainer<Outfit> outfits = BaseRepository.outfits();
		String message = "";
		Object result = null;
		try {
			result = outfits.getByName(type);
		} catch (ItemNotFoundException | NotSupportedException e) {
			message += e.getMessage();
		}
		return new DatabaseResult(result, message);
	}
	
	public final IContainer<Outfit> getOutfits(){
		return BaseRepository.outfits();
	}

}
