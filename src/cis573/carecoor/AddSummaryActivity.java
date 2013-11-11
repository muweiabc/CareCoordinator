package cis573.carecoor;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AddSummaryActivity extends Activity {

	private Intent receivedIntent;
	private Button confirmButton;
	private Button backButton;
	private TextView medicineView;
	private TextView durationView;
	private TextView hoursView;
	private TextView daysView;
	
	private String medicine;
	private String duration;
	private String hours;
	private String days;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		receivedIntent=getIntent();
		medicine=receivedIntent.getStringExtra("medicine");
		duration=receivedIntent.getStringExtra("duration");
		hours=receivedIntent.getStringExtra("hours");
		days=receivedIntent.getStringExtra("days");
		
		setContentView(R.layout.activity_add_summary);
		confirmButton=(Button)findViewById(R.id.confirm);
		backButton=(Button)findViewById(R.id.back_summary);
		confirmButton.setOnClickListener(listener);
		backButton.setOnClickListener(listener);
		
		medicineView=(TextView)findViewById(R.id.summary1);
		durationView=(TextView)findViewById(R.id.summary2);
		hoursView=(TextView)findViewById(R.id.summary3);
		daysView=(TextView)findViewById(R.id.summary4);
		
		medicineView.setText("Medicine : "+medicine);
		durationView.setText("Duration : "+duration);
		hoursView.setText("Hours : "+hours);
		daysView.setText("Days : "+days);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_summary, menu);
		return true;
	}
	
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id=v.getId();			

			if(id==R.id.confirm){		
				setResult(RESULT_OK);
				finish();
			}else
			if(id==R.id.back_summary){
				setResult(RESULT_CANCELED);
				finish();
			}
		}
	};
}
