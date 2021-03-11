package channel;

import enums.TimePeriod;

/**
 * meeting class holds date and time values for an object
 */
public class Meeting {
	private String date;
	private String day;
	private String hour;
	private String minute;
	private TimePeriod period;

	/**
	 * Default constructor
	 */
	public Meeting() {
		this.date = null;
	}
	
	/**
	 * @param date which is given in format that "DAY HH:SS PERIOD" periods are AM and PM
	 * From input it parses this to day hour second and period values
	 */
	public Meeting(String date) {
		this.date = date;
		parseDate();
	}

	/**
	 * Getter for date value
	 * @returns date of the meeting
	 */
	public String getDate() {
		return date;
	}

	/**
	 * setter for date
	 * @param date is format "DAY HH:SS PERIOD" periods are AM and PM
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @returns the day of the meeting
	 */
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public TimePeriod getPeriod() {
		return period;
	}

	public void setPeriod(TimePeriod period) {
		this.period = period;
	}

	/**
	 * Parses given date 
	 */
	private void parseDate()
	{
		String[] splittedDate = this.date.split(" ");
		String[] splittedHour = splittedDate[1].split(":");
		setDay(splittedDate[0]);
		setHour(splittedHour[0]);
		setMinute(splittedHour[1]);
		switch (splittedDate[2]) {
		case "AM": {
			
			setPeriod(TimePeriod.AM);
			break;
		}
		case "PM": {
			
			setPeriod(TimePeriod.PM);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + splittedDate[2]);
		}
		
	}
	
	public void updateDate(String date)
	{
		this.date = date;
		parseDate();
	}
	
	public String toString() {
		String string=this.getDate();
		if(string==null)
			string = "";
		return string;
	}
}
