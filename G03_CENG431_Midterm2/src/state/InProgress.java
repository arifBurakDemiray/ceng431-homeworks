package state;

public class InProgress extends State{

	@Override
	public void nextState(ProductState state) {
		state.setState(new Completed());
	}
	
	@Override
	public void backState(ProductState state) {
		state.setState(new NotStarted());
	}

	@Override
	public String getState() {
		return "InProgress";
	}

}
