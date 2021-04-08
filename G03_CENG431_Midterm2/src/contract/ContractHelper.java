package contract;

import storage.IContainer;
import user.User;

public class ContractHelper {

	protected static String toString(Object users) {
		String value = "[";
		if (users instanceof IContainer<?>) {
			if (users.getClass().getAnnotatedSuperclass().toString().contains("User")) {
				IContainer<User> tempUsers = (IContainer<User>) users;
				for (User user : tempUsers) {
					value += "\"" + user.getUserName() + "\",";
				}
				if (value.endsWith(",")) { // if ends with , ignore it
					value = value.substring(0, value.length() - 1);
				}
				value += "]";
			}
		}
		return value;
	}

}
