package cis573.carecoor.reminder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import cis573.carecoor.bean.Schedule;
import cis573.carecoor.data.DataCenter;
import cis573.carecoor.data.ScheduleCenter;
import cis573.carecoor.utils.Const;
import cis573.carecoor.utils.Logger;

public class ReminderCenter {

	public static final String TAG = "ReminderCenter";
	
	public static void addNextReminder(Context context, Schedule schedule) {
		if(schedule == null) {
			return;
		}
		Date next = ScheduleCenter.getNextUntakeScheduledTime(context, schedule);
		if(next != null) {
			Intent intent = getReminderIntent(context, schedule, next);
			if(intent != null) {
				AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
				PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
				if(pIntent != null) {	// Cancel previous alarm
					alarm.cancel(pIntent);
				}
				alarm.set(AlarmManager.RTC_WAKEUP, next.getTime(),
						PendingIntent.getBroadcast(context, 0, intent, 0));
				Logger.i(TAG, "Alarm set, ring time: " + next.toString());
			}
		}
	}
	
	public static void resetAllReminders(Context context) {
		List<Schedule> schedules = DataCenter.getSchedules(context);
		if(schedules != null) {
			for(Schedule schedule : schedules) {
				addNextReminder(context, schedule);
			}
		}
	}
	
	private static Intent getReminderIntent(Context context, Schedule schedule, Date plan) {
		if(schedule == null) {
			return null;
		}
		Intent intent = new Intent(Const.ACTION_REMINDER_ALARM);
		intent.setPackage(context.getPackageName());
		intent.setData(Uri.parse(getReminderId(schedule)));
		intent.putExtra(Const.EXTRA_SCHEDULE, schedule);
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(plan);
		intent.putExtra(Const.EXTRA_PLANNED_HOUR, cal.get(Calendar.HOUR_OF_DAY));
		return intent;
	}
	
	private static String getReminderId(Schedule schedule) {
		return String.format("reminder://alarm#schedule=%d",
				schedule.getCreateDate().getTime());
	}
}
