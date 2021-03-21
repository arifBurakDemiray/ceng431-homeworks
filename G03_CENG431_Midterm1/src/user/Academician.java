package user;

import java.util.Locale;

import team.Team;

public abstract class Academician extends User {

	/**
	 * Constructor for academician class
	 * 
	 * @param name
	 * @param id
	 * @param password
	 * @param email
	 */
	public Academician(String name, String id, String password, String email) {
		super(name, id, password, email);
	}

	@Override
	public String toString() {
		String teams = "";
		for (Team item : this.getTeams().getContainer()) {

			try {
				item.getOwners().getById(this.getId());
				teams = teams + "O-" + item.getId() + ",";// If he is owner of a team print it with O-XXXXXX
			} catch (Exception e) {
				teams = teams + item.getId() + ",";
			}
		}
		if (teams.endsWith(",")) {
			teams = teams.substring(0, teams.length() - 1);
		}

		String info = (this.getSimpleName() + "," + getName() + "," + getId() + "," + getEmail() + "," + getPassword()
				+ "," + teams);
		return info;
	}
	
	/*
	 * It sets the email which is created with academician domain
	 */
	@Override
	protected void createEmail() {
		String name = getName();
		String[] splittedName = name.split(" ");
		String firstName = splittedName[0];
		String lastName = splittedName[splittedName.length - 1];
		String email = (firstName + lastName).toLowerCase(Locale.US) + "@iyte.edu.tr";
		setEmail(email);

	}
	
	
}
