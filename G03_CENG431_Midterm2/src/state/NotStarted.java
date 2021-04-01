package state;

public class NotStarted extends State{

	@Override
	public void nextState(ProductState state) {
		state.setState(new InProgress());
	}

	@Override
	public String getState() {
		return "NotStarted";
	}

}
