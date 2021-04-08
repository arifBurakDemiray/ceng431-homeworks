package contract;

public abstract class Contract {

	private Object contractee;
	private Object contracter;
	
	public Contract(Object contractee, Object contracter) {
		this.contractee = contractee;this.contracter=contracter;
	}

	/**
	 * @return the product
	 */
	public Object getContractee() {
		return this.contractee;
	}

	/**
	 * @return the user
	 */
	public Object getContracter() {
		return this.contracter;
	}
	
	public abstract String toString();
	
}
