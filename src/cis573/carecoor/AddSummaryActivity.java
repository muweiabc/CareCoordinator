package cis573.carecoor;

import android.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class AddSummaryActivity extends Activity {

	private Button confirmButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		confirmButton=(Button)findViewById(R.id.bt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}

}
