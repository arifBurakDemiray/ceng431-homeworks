package fileio;

import storage.IContainer;
import team.Team;
import user.User;

public interface IFileIO {
	public IContainer<Team> readTeams(String filePath);

	public IContainer<User> readUsers(IContainer<Team> teams, String filePath);

	public boolean writeTeams(IContainer<Team> teams, String filePath);

	public boolean writeUsers(IContainer<User> users, String filePath);
}
