package cis573.carecoor;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import android.R.integer;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import cis573.carecoor.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the
 * ListView with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ReminderFragment extends Fragment implements OnItemSelectedListener
{
	private Button addDrug;
	private Button showDrugs;
	private Button addApptButton;
	private TextView nextapptTextView;
	private TextView nextrefillTextView;
	private Calendar nextRefillDate;
	private Calendar nextApptDate;
	private Spinner doseSpinner;
	
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ReminderFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		nextRefillDate=Calendar.getInstance();
		nextApptDate=Calendar.getInstance();
		//ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dose_array, android.R.layout.simple_spinner_item);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.reminder_fragment, container, false);
		
		addDrug=(Button)view.findViewById(R.id.addDrug);
		showDrugs=(Button)view.findViewById(R.id.showdrugs);
		addApptButton=(Button)view.findViewById(R.id.addappt);
		nextapptTextView=(TextView)view.findViewById(R.id.nextappt);
		nextrefillTextView=(TextView)view.findViewById(R.id.nextrefill);
		doseSpinner=(Spinner) view.findViewById(R.id.dose_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),R.array.dose_array, android.R.layout.simple_spinner_item);
		//ArrayAdapter <String> adap= new ArrayAdapter(this, android.R.layout.simple_spinner_item, R.array.dose_array); 
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		doseSpinner.setAdapter(adapter);
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String refilldate=df.format(nextRefillDate.getTime());
		String apptdate=df.format(nextApptDate.getTime());
		nextapptTextView.setText("Next Appointment Date is: "+refilldate);
		nextrefillTextView.setText("Next Refill Date is: "+apptdate);
		
		addDrug.setOnClickListener(listener);
		showDrugs.setOnClickListener(listener);
		addApptButton.setOnClickListener(listener);
		return view;
	}
	
	

	private TimePickerDialog.OnTimeSetListener cb=new  TimePickerDialog.OnTimeSetListener(){
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {		
		}		
	};
	
	private DatePickerDialog.OnDateSetListener cb2=new  DatePickerDialog.OnDateSetListener(){

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private OnClickListener listener=new OnClickListener() {		
		@Override
		public void onClick(View v) {
			int id=v.getId();
			if(id==R.id.showdrugs){
				Intent intent=new Intent(getActivity(), DruglistActivity.class);
				//startActivityForResult(intent,0);
				startActivity(intent);
			}else
			if(id==R.id.addDrug){
				/*
				Intent intent = new Intent(DashboardScreen.this, ServiceClass.class);
				PendingIntent pintent = PendingIntent.getService(DashboardScreen.this, 0, intent, 0);
				AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
				alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 30*1000, pintent);*/
				
				TimePickerDialog pick=new TimePickerDialog(getActivity(),cb,1,2,true);
				pick.show();
				AlarmManager manager=(AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
				manager.set(AlarmManager.RTC, 10000, null);
			}else 
			if(id==R.id.addappt){
				DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),cb2,1,1,1);
				datePickerDialog.show();
			}
		}
	};
	/*
	public void display(View view){
		Intent intent=new Intent(getActivity(), DruglistActivity.class);
		//startActivityForResult(intent,0);
		startActivity(intent);
	}*/

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}

	