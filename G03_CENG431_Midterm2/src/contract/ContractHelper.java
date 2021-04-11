package contract;

import storage.IContainer;
import user.User;

/**
 * This class helps an user container to write in a json file like a json array
 *
 */
public class ContractHelper {

	/**
	 * This function modifies an user container to a json array
	 * 
	 * @param users to convert a json array
	 * @returns json array like string value
	 */
	protected static String toString(Object users) {
		String value = "["; // start json array
		if (users instanceof IContainer<?>) { // if it is container
			if (users.getClass().getAnnotatedSuperclass().toString().contains("User")) {// if it is user container
				@SuppressWarnings("unchecked") // It is checked double time but it gives cast error
				IContainer<User> tempUsers = (IContainer<User>) users; // cast it to IContainer<User>
				for (User user : tempUsers) {// for each loop
					value += "\"" + user.getUserName() + "\",";// add user names to json array value
				}
				if (value.endsWith(",")) { // if ends with comma ignore it
					value = value.substring(0, value.length() - 1);
				}
			}
		}
		value += "]"; // end json array
		return value; // return json array
	}

}
