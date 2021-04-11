package state;

public class InProgress extends State{
	
	
	/**
	 * The function updates the state of given product state object as Completed state object.
	 * 
	 * @param state product state object
	 */
	@Override
	public void nextState(ProductState state) {
		state.setState(new Completed());
	}
	
	/**
	 * The function updates the state of given product state object as NotStarted state object.
	 * 
	 * @param state product state object
	 */
	@Override
	public void backState(ProductState state) {
		state.setState(new NotStarted());
	}
	
	
	/**
	 * The function returns the state name
	 * 
	 * @return String state name
	 */
	@Override
	public String getState() {
		return "InProgress";
	}

}
