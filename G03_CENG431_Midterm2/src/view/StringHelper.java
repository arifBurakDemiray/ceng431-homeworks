package view;

public class StringHelper {

	/**
	 * The function is a helper function which edits the given string according to
	 * tokens to make gotten string more readable
	 * 
	 * @param products = products tree string
	 * @return String of cleared products tree string
	 */
	protected static String printProductTree(String products) {
		String[] tokens = { "\"", ": {", "{", "}", "type :", "Assembly", "Admin", "Manager", "Part", "," };
		String returnedString = clearOccurences(tokens, products, "");
		String[] tokens1 = { ":" };
		returnedString = clearOccurences(tokens1, returnedString, " =");
		returnedString = returnedString.replace("type =", "-----");
		return returnedString;
	}

	/**
	 * The function replaces the given tokens with given value in the string
	 * 
	 * @param tokens = given tokens which are done be replaced
	 * @param item   = given string
	 * @param value  = value to replace
	 * @return
	 */
	protected static String clearOccurences(String[] tokens, String item, String value) {
		int len = tokens.length;
		String string = item;
		for (int i = 0; i < len; i++) {
			string = string.replace(tokens[i], value);

		}
		return string;
	}

}
