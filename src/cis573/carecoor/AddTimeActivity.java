package cis573.carecoor;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class AddTimeActivity extends Activity {
	
	private Button nextButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_time);
		nextButton=(Button)findViewById(R.id.next);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_time, menu);
		return true;
	}
	
	public void f(View v){
		Intent intent=new Intent(this,AddSummaryActivity.class);
		startActivity(intent);
	}

}


