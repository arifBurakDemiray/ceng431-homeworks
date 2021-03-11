package team;

import channel.Channel;
import exception.ItemExistException;
import user.Academician;
import user.User;

public interface ITeamManagement {

	public void setTeam(Team team);

	public void addChannel(Channel ch) throws ItemExistException;

	public void addMember(User user) throws ItemExistException;

	public void addTeamOwner(Academician user) throws ItemExistException;

}
