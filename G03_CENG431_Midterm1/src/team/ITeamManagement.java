package team;

import channel.Channel;
import exception.ItemExistException;
import exception.UnauthorizedUserOperationException;
import user.Academician;
import user.User;

public interface ITeamManagement {

	public void addChannel(Channel ch) throws ItemExistException;

	public void addMember(User user) throws ItemExistException, UnauthorizedUserOperationException;

	public void addTeamOwner(Academician user) throws ItemExistException, UnauthorizedUserOperationException;

	public void removeChannel(Channel ch);

	public void removeMember(User user) throws UnauthorizedUserOperationException;

	public void removeTeamOwner(User user) throws UnauthorizedUserOperationException;

	public void setTeam(Team team);

}
