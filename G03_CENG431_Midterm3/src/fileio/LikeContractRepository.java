package fileio;

public class LikeContractRepository extends ContractRepository {

	public LikeContractRepository() {
		super(BaseRepository.likes());
	}

	public void addLikedOutfit(String userName, String outfitId) {
		super.add(userName, outfitId);
	}

	public void removeLikedOutfit(String userName, String outfitId) {
		super.remove(userName, outfitId);
	}

	public boolean userHasLiked(String userName, String outfitId) {
		return super.has(userName, outfitId);
	}

}
