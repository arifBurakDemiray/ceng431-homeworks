package channel;

/**
 * This class is to hold the channel information which is given by a csv file.
 * It is a abstract class and it has two child class named Private and Meeting
 * channel
 */
public abstract class Channel {
	private Meeting meeting; // Meeting date and hour
	private String name; // Name of the channel

	/**
	 * Constructor of the superclass Channel
	 * 
	 * @param meeting that is channel's date and time
	 * @param name    that is channel's name
	 */
	public Channel(Meeting meeting, String name) {
		this.meeting = meeting;
		this.name = name;
	}

	/**
	 * Returns the meeting that holds date and time informations of the channel
	 * 
	 * @return the meeting
	 */
	public Meeting getMeeting() {
		return this.meeting;
	}

	/**
	 * Returns the name of the Channel
	 * 
	 * @return name of the channel
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * The function controls that given user is equal to this user 
	 * and returns a boolean
	 *
	 * @return name of the channel
	 */
	@Override
	public boolean equals(Object obj) {
		Channel ch = ((Channel) obj);
		if (ch.getName().equals(this.getName()))
			return true;
		return false;
	}

	/**
	 * The function updates the date of the meeting of the channel with the given
	 * date.
	 */
	public void updateMeetingDate(String date) {
		this.meeting.updateDate(date);
	}

	/**
	 * @return the String value of the channel
	 */
	public String toString() {
		return this.getName() + "," + this.getMeeting().toString();
	}
	
	

}
