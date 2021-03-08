package team;

import java.util.List;

import channel.Channel;
import storage.UserContainer;
import user.TeamOwner;

public class Team {

	private String name;
	private List<Channel> meeting_ch_list;
	private UserContainer memberUsers;
	private List<TeamOwner> owners;
}
