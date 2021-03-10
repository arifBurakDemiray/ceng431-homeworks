package channel;


import exception.ItemExistException;
import storage.IContainer;
import storage.IdContainer;


public class PrivateChannel extends Channel{
	
	private IContainer<String> participants;
	public PrivateChannel(Meeting meeting) {
		super(meeting);
		this.participants = new IdContainer();
	}
	
	public boolean addParticipant(String participantId) throws ItemExistException {
		try {
			return participants.add(participantId);
		}
		catch(ItemExistException e)
		{
			return false;
		}
	}

}
