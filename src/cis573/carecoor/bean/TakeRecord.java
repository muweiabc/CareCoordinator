package cis573.carecoor.bean;

import java.io.Serializable;
import java.util.Date;

public class TakeRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6766320305558216819L;

	public static final String TAG = "TakeRecord";
	
	private Schedule schedule;
	private Date takeTime;
	private int planned;
	private int delay;
	
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

	public int getPlanned() {
		return planned;
	}

	public void setPlanned(int planned) {
		this.planned = planned;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
