package cis573.carecoor.bean;

import android.R.integer;

public class Reminder {
	private String medicine=null;
	private String timeString=null;
	private String duration=null;
	
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medince) {
		medicine = medince;
	}
	public String getTimeString() {
		return timeString;
	}
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
}
