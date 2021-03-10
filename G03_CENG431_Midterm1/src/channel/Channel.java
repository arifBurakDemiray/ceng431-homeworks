package channel;


public abstract class Channel {
	private Meeting meeting;
	public Channel(Meeting meeting) {
		this.meeting = meeting;
	}
	/**
	 * @return the meeting
	 */
	public Meeting getMeeting() {
		return this.meeting;
	}
	
	
}
