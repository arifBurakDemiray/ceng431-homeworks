package contract;

import storage.IContainer;


/**
 *This class represents contract between a manager and an employee
 */
public class ContractUserOutfitsLikes extends Contract {

	public ContractUserOutfitsLikes(String userName, IContainer<String> outfitsLiked) {
		super(userName, outfitsLiked);
	}

	public String toString() {//toString method modified to write in a json file
		String userName = (String) this.getContractee();
		//this helper class is for help users to write in a json file
		String outiftsIds =this.getContracter().toString();
		String jsonValue = "\"" + userName + "\":\"" + outiftsIds+"\",";
		return jsonValue;
	}


	@Override
	public boolean equals(Object obj) {
		return obj.equals((String)this.getContractee());
	}
	
}
