package cis573.carecoor.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Schedule implements Serializable {

	private static final long serialVersionUID = 6606963513717084253L;

	public static final String TAG = "Schedule";
	
	private Date createDate;
	private Medicine medicine;
	private List<Integer> hours;
	private List<Integer> days;
	private int duration;
	
	public Schedule(Date createDate) {
		this.createDate = createDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	public List<Integer> getHours() {
		return hours;
	}
	public void setHours(List<Integer> hours) {
		this.hours = hours;
	}
	public List<Integer> getDays() {
		return days;
	}
	public void setDays(List<Integer> days) {
		this.days = days;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
