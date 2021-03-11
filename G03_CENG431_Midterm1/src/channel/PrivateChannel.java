package channel;



import storage.IContainer;
import storage.IdContainer;


public class PrivateChannel extends Channel{
	
	private IContainer<String> participants;
	public PrivateChannel(Meeting meeting) {
		super(meeting);
		this.participants = new IdContainer();
	}
	
	public boolean addParticipant(String participantId) {

		return participants.add(participantId);

	}

}
