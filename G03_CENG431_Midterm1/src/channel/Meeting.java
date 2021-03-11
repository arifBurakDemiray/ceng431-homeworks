package channel;

import enums.TimePeriod;

public class Meeting {
	private String date;
	private String day;
	private String hour;
	private String minute;
	private TimePeriod period;

	
	public Meeting() {
		this.date = null;
	}
	
	public Meeting(String date) {
		this.date = date;
		parseDate();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

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
	
	public String toString() {
		String string=this.getDate();
		if(string==null)
			string = "";
		return string;
	}
}
