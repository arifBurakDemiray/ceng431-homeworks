package app;


import factory.Creator;
import fileio.FileController;
import view.AppView;
import view.View;

public class Main {
	public static void main(String[] args) throws Exception {
		Creator creator = new Creator();
		FileController fController = new FileController(creator);
		View view = new AppView(fController,creator);
		view.start();
	}
}
