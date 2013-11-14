package cis573.carecoor.data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cis573.carecoor.bean.Schedule;
import cis573.carecoor.bean.TakeRecord;
import cis573.carecoor.utils.Utils;
import android.content.Context;

public class ScheduleCenter {

	public static final String TAG = "ScheduleCenter";
	
	public static final int SCHEDULE_NO_TODAY = -1;
	public static final int SCHEDULE_ENDED = -2;
	
	public static int getScheduleStatus(Context context, Schedule schedule) {
		int duration = schedule.getDuration();
		List<Integer> days = schedule.getDays();
		Calendar now = Calendar.getInstance(Locale.US);
		if(duration > 0) {	// Has duration
			Calendar end = Calendar.getInstance(Locale.US);
			end.setTime(schedule.getCreateDate());
			end.add(Calendar.DATE, duration);
			if(now.after(end)) {
				return SCHEDULE_ENDED;
			}
		}
		if(days != null) {	// Not every day
			if(!days.contains(now.get(Calendar.DAY_OF_WEEK))) {
				return SCHEDULE_NO_TODAY;
			}
		}
		// Check how many times taken today
		int times = 0;
		List<TakeRecord> records = DataCenter.getTakeRecords(context);
		if(records != null) {
			Date today = new Date();
			for(TakeRecord record : records) {
				if(Utils.inSameDay(record.getTakeTime(), today)) {
					times++;
				}
			}
		}
		return times;
	}
}
