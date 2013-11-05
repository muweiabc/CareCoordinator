package cis573.carecoor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import android.R.integer;
import android.app.Activity;
import android.app.AlarmManager;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
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
public class DrugReminderFragment extends Fragment 
{
	private Button addDrug;
	private Button showDrugs;
	private TimePickerDialog.OnTimeSetListener cb=new  TimePickerDialog.OnTimeSetListener(){

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
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
			}
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
			}
		}
	};
	/*
	public void display(View view){
		Intent intent=new Intent(getActivity(), DruglistActivity.class);
		//startActivityForResult(intent,0);
		startActivity(intent);
	}*/
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public DrugReminderFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.drug_reminder_fragment, container, false);
		addDrug=(Button)view.findViewById(R.id.addDrug);
		showDrugs=(Button)view.findViewById(R.id.showdrugs);
		addDrug.setOnClickListener(listener);
		// Set the adapter
		showDrugs.setOnClickListener(listener);
		return view;
	}
}

	