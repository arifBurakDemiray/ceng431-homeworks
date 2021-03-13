package storage;


import channel.Channel;
import exception.ItemNotFoundException;
import exception.NotSupportedException;


public class ChannelContainer extends Container<Channel> {


	@Override
	public Channel getById(String id) throws NotSupportedException {
		throw new NotSupportedException("src.storage.ChannelContainer.getById() function is not supported for ChannelContainer.");
	}

	@Override
	public Channel getByName(String name) throws ItemNotFoundException{
		Channel found = null;
		for(Channel ch : this.getContainer()) {
			if(ch.getName().equals(name)){
				found = ch;
				break;
			}
		}
		if(found == null)
		{
			throw new ItemNotFoundException();
		}
		else
		{
			return found;
		}
	}

}



	

	

	


