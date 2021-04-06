package factory;

import java.util.Random;

public class RandomFactory {

	private static String createRandomId() {
		Random rand = new Random();
		Integer id = rand.nextInt(99999) + 1;
		return (String.valueOf(id));
	}
	
	protected static String randomId() {
		return createRandomId();
	}
	
	
}
