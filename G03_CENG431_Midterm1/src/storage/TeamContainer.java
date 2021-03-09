package storage;
import exception.ItemNotFoundException;
import team.Team;
public class TeamContainer extends Container<Team> {

	@Override
	public Team getById(String id) throws ItemNotFoundException {
		Team returnedTeam = null;
		for(Team team : getContainer()) {
			if(team.getId().equals(id)){
				
				returnedTeam = team;
			}
		}
		if(returnedTeam == null)
		{
			throw new ItemNotFoundException();
		}
		else
		{
			return returnedTeam;
		}
		
	}


	

	
	
}
