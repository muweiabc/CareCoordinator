package cis573.carecoor.reminder;

import cis573.carecoor.R;
import cis573.carecoor.TakeMedicineActivity;
import cis573.carecoor.bean.Medicine;
import cis573.carecoor.bean.Schedule;
import cis573.carecoor.data.DataCenter;
import cis573.carecoor.utils.Const;
import cis573.carecoor.utils.Utils;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class ReminderReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if(Const.ACTION_REMINDER_ALARM.equals(intent.getAction())) {
			Schedule schedule = (Schedule) intent.getSerializableExtra(Const.EXTRA_SCHEDULE);
			int planned = intent.getIntExtra(Const.EXTRA_PLANNED_HOUR, -1);
			if(schedule != null) {
				showNotification(context, schedule, planned);
			}
		}
	}
	
	private void showNotification(Context context, Schedule schedule, int planned) {
		String medName = "";
		String plannedStr = "";
		Medicine med = null;
		if(schedule != null) {
			med = schedule.getMedicine();
		}
		if(med != null) {
			medName = med.getName();
		}
		if(planned >= 0) {
			plannedStr = Utils.get12ClockTime(planned);
		}
		String text = context.getString(R.string.reminder_text, medName, plannedStr);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
		.setTicker(context.getString(R.string.reminder_ticker))
		.setContentTitle(context.getString(R.string.reminder_title))
		.setContentText(text)
		.setSmallIcon(R.drawable.ic_launcher)
		.setDefaults(Notification.DEFAULT_SOUND)
		.setAutoCancel(true);
		
		Intent newIntent = new Intent(context, TakeMedicineActivity.class);
		newIntent.putExtra(Const.EXTRA_SCHEDULE, schedule);
		newIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, newIntent, 0);
		builder.setContentIntent(pIntent);
		
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(DataCenter.getScheduleId(context, schedule), builder.build());
	}
}
