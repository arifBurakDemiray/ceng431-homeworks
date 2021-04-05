package product;

public class StringHelper{

	public String clearOccurences(String[] tokens,String item,String value) {
		int len = tokens.length;
		String string = item;
		for(int i=0;i<len;i++) {
			string = string.replace(tokens[i], value);
		}
		return string;
	}
}
