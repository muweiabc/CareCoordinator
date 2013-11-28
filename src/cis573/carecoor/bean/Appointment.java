package cis573.carecoor.bean;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TAG = "Appointment";
	
	private Date date;
	private String detail;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
