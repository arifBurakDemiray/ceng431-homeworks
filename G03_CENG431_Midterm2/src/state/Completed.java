package state;

public class Completed extends State {

	@Override
	public void nextState(ProductState state) {
	}

	/**
	 * The function updates the state of given product state object as InProgress
	 * state object.
	 * 
	 * @param state product state object
	 */
	@Override
	public void backState(ProductState state) {
		state.setState(new InProgress());
	}

	/**
	 * The function returns the state name
	 * 
	 * @return String state name
	 */
	@Override
	public String getState() {
		return "Completed";
	}

}
