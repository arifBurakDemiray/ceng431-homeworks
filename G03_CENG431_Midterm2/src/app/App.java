package app;


import exception.UnauthorizedUserException;
import view.AppView;
import view.View;

public class App {
	public static void main(String[] args)  {
		View view = new AppView();
		try {
			view.start();
		} catch (UnauthorizedUserException e) {}
	}
}
