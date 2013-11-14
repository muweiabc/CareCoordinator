package cis573.carecoor.bean;

import java.io.Serializable;
import java.util.Date;

public class TakeRecord implements Serializable {

	private static final long serialVersionUID = 5252408419699703707L;

	public static final String TAG = "TakeRecord";
	
	private Schedule schedule;
	private Date takeTime;
	
	public TakeRecord(Schedule schedule, Date takeTime) {
		this.schedule = schedule;
		this.takeTime = takeTime;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public Date getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}
}
