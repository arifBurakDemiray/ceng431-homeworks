package channel;



import storage.IContainer;
import storage.IdContainer;


public class PrivateChannel extends Channel{
	
	private IContainer<String> participants;
	public PrivateChannel(Meeting meeting,String name) {
		super(meeting,name);
		this.participants = new IdContainer();
	}
	
	public boolean addParticipant(String participantId) {

		return participants.add(participantId);

	}
	
	@Override
	public String toString() {
		return super.toString()+this.participants.toString();
	}

}
