package factory;

import java.util.Random;

/**
 * This class creates a random id
 *
 */
public class RandomFactory {

	// random id creation
	private static String createRandomId() {
		Random rand = new Random();
		Integer id = rand.nextInt(99999) + 1;
		return (String.valueOf(id));
	}

	// random id giver
	protected static String randomId() {
		return createRandomId();
	}

}
