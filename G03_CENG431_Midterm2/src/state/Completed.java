package state;

public class Completed extends State{

	@Override
	public void nextState(ProductState state) {
		System.out.println("This product is already completed.");
	}

	@Override
	public String getState() {
		return "Completed";
	}

}