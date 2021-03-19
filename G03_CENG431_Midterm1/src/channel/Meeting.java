package channel;

import enums.Day;
import enums.TimePeriod;

/**
 * meeting class holds date and time values for an object
 */
public class Meeting {
	private String date; // date which is in format 'Day HH:MM AM/PM'
	private Day day; // Day Enum
	private String hour; // Hour in format HH
	private String minute; // Minute in format MM
	private TimePeriod period; // Time Period Enum

	/**
	 * Default constructor
	 */
	public Meeting() {
		this.date = null;
	}

	/**
	 * @param date which is given in format that "DAY HH:SS PERIOD" periods are AM
	 *             and PM From input it parses this to day hour second and period
	 *             values
	 */
	public Meeting(String date) {
		this.date = date;
		parseDate();
	}

	/**
	 * Getter for date value
	 * 
	 * @returns date of the meeting
	 */
	public String getDate() {
		return date;
	}

	/**
	 * setter for date
	 * 
	 * @param date is format "DAY HH:SS PERIOD" periods are AM and PM
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @returns the day of the meeting
	 */
	public Day getDay() {
		return day;
	}

	/**
	 * setter for day
	 * 
	 * @param day gotten Day Enum of the meeting
	 */
	public void setDay(Day day) {
		this.day = day;
	}

	/**
	 * @returns the hour of the meeting
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * setter for hour
	 * 
	 * @param hour of the meeting
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}

	/**
	 * @returns the minute of the meeting
	 */
	public String getMinute() {
		return minute;
	}

	/**
	 * setter for minute
	 * 
	 * @param minute of the meeting
	 */
	public void setMinute(String minute) {
		this.minute = minute;
	}

	/**
	 * @returns the time period enum of the meeting
	 */
	public TimePeriod getPeriod() {
		return period;
	}

	/**
	 * setter for period
	 * 
	 * @param period enum of the meeting
	 */
	public void setPeriod(TimePeriod period) {
		this.period = period;
	}

	/**
	 * Parses given date, set day, hour, minute and time period. If date is in wrong
	 * type, throws an exception
	 * 
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	private void parseDate() throws IllegalArgumentException, NumberFormatException, ArrayIndexOutOfBoundsException {
		String[] splittedDate = this.date.split(" "); // split date
		String[] splittedHour = splittedDate[1].split(":"); // split hour as hour and minute

		// Set attributes according to splitted values
		setDay(Day.valueOf(splittedDate[0]));
		setHour(splittedHour[0]);
		setMinute(splittedHour[1]);

		// Control that hour and minute are valid
		boolean hour = (0 <= Integer.valueOf(getHour()) && (Integer.valueOf(getHour()) <= 24));
		boolean minute = (0 <= Integer.valueOf(getMinute()) && Integer.valueOf(getMinute()) <= 59);

		// If hour or minute are not valid, throw an exception.
		if (!hour || !minute) {
			throw new NumberFormatException("Wrong Hour and Minute Format:" + getHour() + ":" + getMinute());
		}

		// Set the time period. If time period is not valid, throw an exception.
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
			throw new IllegalArgumentException("Wrong time period, please write AM or PM: " + splittedDate[2]);
		}

	}

	/*
	 * The function gets the given date which is in format 'Day HH:MM AM/PM'. and
	 * sets the date. After that it invokes the parser to set date specifically.
	 * 
	 * @param date : given date which is in format 'Day HH:MM AM/PM'
	 */
	public void updateDate(String date) {
		this.date = date;
		parseDate();
	}

	/*
	 * Print date info
	 */
	public String toString() {
		String string = this.getDate();
		if (string == null)
			string = "";
		return string;
	}
}
