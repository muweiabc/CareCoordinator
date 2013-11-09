package cis573.carecoor;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddSummaryActivity extends Activity {

	private Button confirmButton;
	private Button backButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_summary);
		confirmButton=(Button)findViewById(R.id.confirm);
		backButton=(Button)findViewById(R.id.back_summary);
		confirmButton.setOnClickListener(listener);
		backButton.setOnClickListener(listener);
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
				Intent result=new Intent();
				setResult(RESULT_OK,result);
				finish();
			}else
			if(id==R.id.back_summary){
				setResult(RESULT_CANCELED);
				finish();
			}
		}
	};


}
