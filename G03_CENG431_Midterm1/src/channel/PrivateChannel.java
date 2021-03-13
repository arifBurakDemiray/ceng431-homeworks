package channel;



import storage.IContainer;
import storage.IdContainer;

import exception.ItemNotFoundException;


public class PrivateChannel extends Channel{
	
	private IContainer<String> participants;
	
	public PrivateChannel(Meeting meeting,String name) {
		super(meeting,name);
		this.participants = new IdContainer();
	}
	
	
	/**
	 * The function adds the given participant id to the participants if it is not added before.
	 *
	 * @param item participant id to add to the container.
	 * @return true/false
	 */
	public boolean addParticipant(String participantId) {
		return participants.add(participantId);
	}
	
	/**
	 * The function tries to remove given participant from the participant list if it is possible
	 * If item is in the list remove it. Else return ItemNotFoundException.
	 * If removing process is not successfull, return the error message.
     *
     * @param item given item to remove from the participants
	 */
	public boolean removeParticipant(String id){
		String removedParticipant = null;
		try {
			removedParticipant = participants.remove(id); //try to remove invoking container.remove method
			if (!id.equals(removedParticipant)) {
				System.out.println("Participant has not been removed");
				return false;
			}
		} catch (ItemNotFoundException e) {
			System.out.println("This user is not found in the participants.");
			return false;
		}
		return true;
	}
	
	public IContainer<String> getParticipants()
	{
		return this.participants;
	}
	
	@Override
	public String toString() {
		return super.toString()+","+"\""+this.participants.toString()+"\"";
	}

	
}
