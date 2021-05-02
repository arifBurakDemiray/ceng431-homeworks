package observation;

/**
 * This interface is a interface for Observers
 */
public interface Observer {

	/**
	 * This function updates observer by given observable and given args
	 * 
	 * @param observable is the caller of the update function
	 * @param args       that is sended by observable
	 */
	public void update(Observable observable, Object args);

}
