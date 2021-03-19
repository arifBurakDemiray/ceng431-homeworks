package storage;

import exception.ItemNotFoundException;
import team.Team;

public class TeamContainer extends Container<Team> {

	/**
	 * The function search the given id belongs to the any team or not. If given id
	 * belongs to a team, it returns the team
	 * 
	 * @param id given team id
	 * @return Team whose id is the same with given id.
	 */
	@Override
	public Team getById(String id) throws ItemNotFoundException {
		Team returnedTeam = null;
		for (Team team : getContainer()) {
			if (team.getId().equals(id)) { //if id equals element's id

				returnedTeam = team;
			}
		}
		if (returnedTeam == null) {
			throw new ItemNotFoundException("There is no team has id "+id);//throw item not found
		} else {
			return returnedTeam;
		}
	}

	/**
	 * The function search the given name belongs to the any team or not. If given
	 * name belongs to a team, it returns the team
	 * 
	 * @param name given team name
	 * @return Team whose name is the same with given name.
	 */
	@Override
	public Team getByName(String name) throws ItemNotFoundException {
		Team found = null;
		for (Team team : this.getContainer()) {
			if (team.getName().equals(name)) {
				found = team;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException("There is no team named "+name);
		} else {
			return found;
		}
	}

}
