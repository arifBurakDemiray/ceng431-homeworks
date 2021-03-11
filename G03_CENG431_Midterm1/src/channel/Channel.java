package channel;


public abstract class Channel {
	private Meeting meeting;
	private String name;
	public Channel(Meeting meeting, String name) {
		this.meeting = meeting;
		this.name = name;
	}
	/**
	 * @return the meeting
	 */
	public Meeting getMeeting() {
		return this.meeting;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		
		return this.getName()+this.getMeeting().toString();
	}
}
