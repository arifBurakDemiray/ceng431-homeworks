package state;

public class ProductState {
	private State state;

	public ProductState(State state) {
		this.state = state;
	}

	public ProductState() {
		this.state = new NotStarted();
	}

	public String getState() {
		return this.state.getState();
	}

	public void nextState() {
		this.state.nextState(this);
	}

	public void backState() {
		this.state.backState(this);
	}
	
	protected void setState(State state) {
		this.state = state;
	}
}
