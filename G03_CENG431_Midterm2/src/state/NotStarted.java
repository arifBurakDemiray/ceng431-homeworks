package state;

public class NotStarted extends State{

	@Override
	public void nextState(ProductState state) {
		state.setState(new InProgress());
	}
	
	@Override
	public void backState(ProductState state) {
		System.out.println("This product is just started.");
	}

	@Override
	public String getState() {
		return "NotStarted";
	}

}
