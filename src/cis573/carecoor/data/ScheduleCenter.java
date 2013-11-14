package cis573.carecoor.data;

import java.util.ArrayList;
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
	
	public static final int SCHEDULE_HAS_TODAY = 0;
	public static final int SCHEDULE_NO_TODAY = -1;
	public static final int SCHEDULE_ENDED = -2;
	
	public static List<TakeRecord> getDayTakeRecordsForScheduleToday(Context context, Schedule schedule) {
		return getDayTakeRecordsForSchedule(context,schedule, new Date());
	}
	
	public static List<TakeRecord> getDayTakeRecordsForSchedule(Context context, Schedule schedule, Date date) {
		if(schedule == null || date == null) {
			return null;
		}
		List<TakeRecord> list = new ArrayList<TakeRecord>();
		List<TakeRecord> records = DataCenter.getTakeRecords(context);
		if(records != null) {
			for(TakeRecord record : records) {
				if(record.getSchedule().equals(schedule)
						&& Utils.inSameDay(record.getTakeTime(), date)) {
					list.add(record);
				}
			}
		}
		return list;
	}
	
	public static int getScheduleStatusSimple(Context context, Schedule schedule) {
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
		return SCHEDULE_HAS_TODAY;
	}
	
	public static int getScheduleStatus(Context context, Schedule schedule) {
		int result = getScheduleStatusSimple(context, schedule);
		if(result >= 0) {	// Check how many times taken today
			List<TakeRecord> records = getDayTakeRecordsForSchedule(context, schedule, new Date());
			result = records != null ? records.size() : 0;
		}
		return result;
	}
}
