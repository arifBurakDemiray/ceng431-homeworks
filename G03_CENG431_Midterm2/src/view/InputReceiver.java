package view;

import java.util.Scanner;

public class InputReceiver {

	private Scanner scanner;

	/**
	 * The function creates a scanner for input processes
	 */
	public InputReceiver() {
		scanner = new Scanner(System.in);
	}

	/**
	 * The function close the scanner
	 */
	public void close() {
		scanner.close();
	}

	/**
	 * The function prints the given menu input text and get an input from user
	 * 
	 * @param printedText
	 * @return input gotten from user
	 */
	public String getString(String printedText) {
		System.out.print(printedText);
		String string = scanner.nextLine();
		return string;
	}

}
