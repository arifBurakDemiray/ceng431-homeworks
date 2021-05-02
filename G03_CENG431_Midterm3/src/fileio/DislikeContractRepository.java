package fileio;

public class DislikeContractRepository extends ContractRepository {

	public DislikeContractRepository() {
		super(BaseRepository.dislikes());
	}

	public void addDislikedOutfit(String userName, String outfitId) {
		super.add(userName, outfitId);
	}

	public void removeDislikedOutfit(String userName, String outfitId) {
		super.remove(userName, outfitId);
	}

	public boolean userHasDisliked(String userName, String outfitId) {
		return super.has(userName, outfitId);
	}

}
