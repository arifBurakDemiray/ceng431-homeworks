package view;


public class StringHelper{

	protected static String clearOccurences(String[] tokens,String item,String value) {
		int len = tokens.length;
		String string = item;
		for(int i=0;i<len;i++) {
			string = string.replace(tokens[i], value);

		}
		return string;
	}
	
	protected static String printProductTree(String products){
/*		String[] tokens = {"\"",": {","{","}",
		        "type :","Assembly"
		              ,"Admin","Manager","Part",","};
		String returnedString = clearOccurences(tokens, products, "");
		String[] tokens1 = {":"};
		returnedString = clearOccurences(tokens1,returnedString," =");
		returnedString = returnedString.replace("type =", "-----"); 
		return returnedString;*/
		String message = "";
		String[] values = products.split("\\{");		
		for(String value:values){
			if(value.contains("Part")){
				
			}
			else
				message+=value+"\n\t";
		}
		
		return message;
	}
		/*int last = products.lastIndexOf("{");
		int first = products.indexOf("}");
		System.out.println(products.replace(products.substring(last, first),""));*/

}

