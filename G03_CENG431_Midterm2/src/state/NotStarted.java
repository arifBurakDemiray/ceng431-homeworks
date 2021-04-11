package state;

public class NotStarted extends State {

	/**
	 * The function updates the state of given product state object as InProgress
	 * state object.
	 * 
	 * @param state product state object
	 */
	@Override
	public void nextState(ProductState state) {
		state.setState(new InProgress());
	}

	@Override
	public void backState(ProductState state) {
		System.out.println("This product is just started.");
	}

	/**
	 * The function returns the state name
	 * 
	 * @return String state name
	 */
	@Override
	public String getState() {
		return "NotStarted";
	}

}
