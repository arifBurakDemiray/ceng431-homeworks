package contract;

import storage.IContainer;


/**
 *This class represents contract between a user and liked/disliked outfits
 */
public class ContractUserOutfitsLikes extends Contract {

	public ContractUserOutfitsLikes(String userName, IContainer<String> outfitsLiked) {
		super(userName, outfitsLiked);
	}

	public String toString() {//toString method modified to write in a json file
		String userName = (String) this.getContractee();
		String outiftsIds =this.getContracter().toString();
		String jsonValue = "\"" + userName + "\":\"" + outiftsIds+"\",";
		return jsonValue;
	}

	/**
	 * The function controls given object is equal to contractee or not
	 */
	@Override
	public boolean equals(Object obj) {
		return obj.equals((String)this.getContractee());
	}
	
}
