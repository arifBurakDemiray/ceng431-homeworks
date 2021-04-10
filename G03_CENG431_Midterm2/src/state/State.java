package state;

public abstract class State {
	public abstract void nextState(ProductState state);
	public abstract void backState(ProductState state);
	public abstract String getState();
}
