package state;

public class InProgress extends State{

	@Override
	public void nextState(ProductState state) {
		state.setState(new Completed());
	}

	@Override
	public String getState() {
		return "InProgress";
	}

}
