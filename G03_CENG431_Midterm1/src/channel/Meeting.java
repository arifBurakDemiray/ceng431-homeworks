package channel;

import enums.TimePeriod;

public class Meeting {
	private String day;
	private String hour;
	private String minute;
	private TimePeriod period;
	public Meeting(String day,String hour, String minute, TimePeriod period) {
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.period = period;
		
	}
	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}
	/**
	 * @return the minute
	 */
	public String getMinute() {
		return minute;
	}
	/**
	 * @return the period
	 */
	public TimePeriod getPeriod() {
		return period;
	}
}
