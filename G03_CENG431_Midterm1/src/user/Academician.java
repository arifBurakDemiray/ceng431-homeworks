package user;

import exception.ItemNotFoundException;
import exception.NotSupportedException;
import team.Team;

public abstract class Academician extends User {

	public Academician(String name, String id, String password, String email) {
		super(name, id, password, email); 
		// TODO Auto-generated constructor stub
	}
  
	@Override 
	public String toString()
	{
		String teams = "";
		for (Team item : this.getTeams().getContainer()) {
			
			try {
				item.getOwners().getById(this.getId());
				teams = teams + "O-"+item.getId()+",";
			} catch (Exception e) {
				teams = teams + item.getId() + ",";
			}
		}
		if (teams.endsWith(",")) {
			teams = teams.substring(0, teams.length() - 1);
		}

		// String className = tempClassName.split(".")[1];
		String info = (this.getSimpleName() + "," + getName() + "," + getId() + "," + getEmail() + "," + getPassword()
				+ "," + teams);
		return info;
	}
}
