package app;


import view.AppView;
import view.View;

public class App {
	public static void main(String[] args) throws Exception {
		View view = new AppView();
		view.start();
	}
}
