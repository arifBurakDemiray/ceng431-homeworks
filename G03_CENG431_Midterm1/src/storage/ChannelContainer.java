package storage;


import channel.Channel;
import exception.NotSupportedException;

public class ChannelContainer extends Container<Channel> {


	@Override
	public Channel getById(String id) throws NotSupportedException {
		throw new NotSupportedException("src.storage.ChannelContainer.getById() function is not supported for ChannelContainer.");
	}

}



	

	

	


