package cis573.carecoor;

import java.util.Calendar;

import cis573.carecoor.bean.Reminder;

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
	private Button backButton;
	private final int code1=1; 
	private Reminder newReminder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_reminder);
		nextButton=(Button)findViewById(R.id.next_reminder);
		chooseButton=(Button)findViewById(R.id.choose);
		backButton=(Button)findViewById(R.id.back_reminder);
		backButton.setOnClickListener(listener);
		nextButton.setOnClickListener(listener);	
	}

	private OnClickListener listener=new OnClickListener(){
		@Override
		public void onClick(View v) {
			int id=v.getId();
			if(id==R.id.choose){
				//Intent intent=new Intent(action1);
				Intent intent=new Intent(AddReminderActivity.this,DruglistActivity.class);
				startActivityForResult(intent, 2);
			}else
			if(id==R.id.next_reminder){
				Intent intent=new Intent(AddReminderActivity.this,AddDurationActivity.class);
				startActivityForResult(intent,code1);
			}
			if(id==R.id.back_reminder){
				//Intent intent=new Intent();
				setResult(RESULT_CANCELED);
				finish();
			}
		}		
	};
	
	@Override
	public void onActivityResult(int requestCode, int resultCode,Intent data){
         if (requestCode == code1) {
             if (resultCode == RESULT_OK) {
                 // A contact was picked.  Here we will just display it
                 // to the user.
                 
                 Intent result =new Intent();
                 setResult(RESULT_OK,result);
                 finish();
             }
         }         
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