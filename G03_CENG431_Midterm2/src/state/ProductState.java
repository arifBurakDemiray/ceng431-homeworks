package state;

public class ProductState {
	private State state; // one of state objects Completed,InProgress,NotStarted

	/**
	 * The constructor for Product state object.
	 * 
	 * @param state = one of state objects Completed,InProgress,NotStarted
	 */
	public ProductState(State state) {
		this.state = state;
	}

	/**
	 * The default constructor for Product state object whose state defined as
	 * NotStarted
	 */
	public ProductState() {
		this.state = new NotStarted();
	}

	/**
	 * The function updates the state as back state.
	 */
	public void backState() {
		this.state.backState(this);
	}

	/**
	 * The function updates the state as next state.
	 */
	public void nextState() {
		this.state.nextState(this);
	}

	public String getState() {
		return this.state.getState();
	}

	protected void setState(State state) {
		this.state = state;
	}
}
