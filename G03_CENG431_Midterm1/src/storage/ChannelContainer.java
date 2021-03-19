package storage;

import channel.Channel;
import exception.ItemNotFoundException;
import exception.NotSupportedException;

public class ChannelContainer extends Container<Channel> {

	/**
	 * The function of the abstract function returns an error about not supporting
	 * that process
	 * 
	 * @param id given channel id
	 * @throws NotSupportedException because channel has no id.
	 */
	@Override
	public Channel getById(String id) throws NotSupportedException {
		throw new NotSupportedException(
				"src.storage.ChannelContainer.getById() function is not supported for ChannelContainer.");
	}

	/**
	 * The function search the given name belongs to the any channel or not. If
	 * given name belongs to a channel, it returns the channel
	 * 
	 * @param name given channel name
	 * @return Channel whose name is the same with given name.
	 */
	@Override
	public Channel getByName(String name) throws ItemNotFoundException {
		Channel found = null;
		for (Channel ch : this.getContainer()) {
			if (ch.getName().equals(name)) { // if name equals element's name
				found = ch;
				break;
			}
		}
		if (found == null) {
			throw new ItemNotFoundException();
		} else {
			return found;
		}
	}

}
