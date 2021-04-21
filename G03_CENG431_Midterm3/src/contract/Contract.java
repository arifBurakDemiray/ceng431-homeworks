package contract;

/**
 * This class represents contracts between user-product and user-user
 *
 */
public abstract class Contract {

	private Object contractee;// Contract owner
	private Object contracter;// contract signer

	/**
	 * Constracts a contract between owner and signer
	 * 
	 * @param contractee
	 * @param contracter
	 */
	public Contract(Object contractee, Object contracter) {
		this.contractee = contractee;
		this.contracter = contracter;
	}

	/**
	 * @return signer of the contract
	 */
	public Object getContractee() {
		return this.contractee;
	}

	/**
	 * @return owner of the product
	 */
	public Object getContracter() {
		return this.contracter;
	}

	public abstract String toString();

}
