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
	
	public String getUsername() {
		System.out.println("Username: ");
		String userName = scanner.nextLine();
		return userName;
	}
	public String getPassword() {
		System.out.println("Password: ");
		String password = scanner.nextLine();
		return password;
	}
}
