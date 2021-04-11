package state;

public abstract class State {

	/**
	 * The function updates the state of given product state as next state
	 * 
	 * @param state product state object
	 */
	public abstract void nextState(ProductState state);

	/**
	 * The function updates the state of given product state as back state
	 * 
	 * @param state product state object
	 */
	public abstract void backState(ProductState state);

	/**
	 * The function returns the state.
	 * 
	 * @return string state name
	 */
	public abstract String getState();
}
