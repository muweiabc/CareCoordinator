package cis573.carecoor;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ChooseMedActivity extends Activity {

	public static final String TAG = "ChooseMedActivity";
	
	private ListView mLvMedicines;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_med_activity);
		initViews();
	}
	
	private void initViews() {
		mLvMedicines = (ListView) findViewById(R.id.choose_med_list);
	}
}
