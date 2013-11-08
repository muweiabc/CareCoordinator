package cis573.carecoor;

import java.util.Calendar;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddReminderActivity extends Activity {
	
	private Button nextButton;
	private Button chooseButton;
	
	private OnClickListener listener=new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Intent intent=new Intent(Intent.ACTION_MAIN,AddDurationActivity.class);
			//startActivity(intent);
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_reminder);
		nextButton=(Button)findViewById(R.id.next);
		chooseButton=(Button)findViewById(R.id.choose);
		//nextButton.setOnClickListener(listener);
	}
	public void f(View v){
		int id=v.getId();
		if(id==R.id.choose){
			Intent intent=new Intent(this,DruglistActivity.class);
			startActivity(intent);
		}else
		if(id==R.id.next){
			Intent intent=new Intent(this,AddDurationActivity.class);
			startActivity(intent);
		}
	}
	/*
	Intent intent = new Intent(DashboardScreen.this, ServiceClass.class);
	PendingIntent pintent = PendingIntent.getService(DashboardScreen.this, 0, intent, 0);
	AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
	alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 30*1000, pintent);
	final Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    
	TimePickerDialog pick=new TimePickerDialog(getActivity(),cb,hour,minute,DateFormat.is24HourFormat(getActivity()));
	pick.show();*/

}
