package view;

import java.util.Scanner;

public class InputReceiver {

	private Scanner scanner;

	public InputReceiver() {
		scanner = new Scanner(System.in);
	}
	public void close() {
		scanner.close();
	}
	
	public String getString(String printedText) {
		System.out.print(printedText);
		String string = scanner.nextLine();
		return string;
	}

}
