package cis573.carecoor;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import cis573.carecoor.bean.Medicine;
import cis573.carecoor.data.MedicineCenter;
import cis573.carecoor.utils.Const;

public class SetScheduleActivity extends Activity {

	public static final String TAG = "SetScheduleActivity";
	
	private TextView mTvMedName;
	private ImageView mIvMedImage;
	private TextView mTvMedInfo1;
	private TextView mTvMedInfo2;
	private TextView mTvTakeTimeSugg;
	private GridLayout mGlTakeTime;
	
	private Medicine mMedicine = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMedicine = (Medicine) getIntent().getSerializableExtra(Const.EXTRA_MEDICINE);
		setContentView(R.layout.set_schedule_activity);
		
		initViews();
		initTakeTimeGrid();
		
		showMedicineInfo();
	}

	private void initViews() {
		mTvMedName = (TextView) findViewById(R.id.set_schedule_medname);
		mIvMedImage = (ImageView) findViewById(R.id.set_schedule_medimage);
		mTvMedInfo1 = (TextView) findViewById(R.id.set_schedule_medinfo1);
		mTvMedInfo2 = (TextView) findViewById(R.id.set_schedule_medinfo2);
		mTvTakeTimeSugg = (TextView) findViewById(R.id.set_schedule_taketime_suggest);
		mGlTakeTime = (GridLayout) findViewById(R.id.set_schedule_taketime_grid);
	}
	
	private void showMedicineInfo() {
		if(mMedicine == null) {
			return;
		}
		mTvMedName.setText(mMedicine.getName());
		mIvMedImage.setImageResource(MedicineCenter.getMedicineImageRes(SetScheduleActivity.this, mMedicine));
		mTvMedInfo1.setText(mMedicine.getDetailedName() + " - " + mMedicine.getCapacity());
		mTvMedInfo2.setText(mMedicine.getInstructions());
		mTvTakeTimeSugg.setText(getString(R.string.set_schedule_take_time_suggest, mMedicine.getTimes()));
	}
	
	private void initTakeTimeGrid() {
		ToggleButton tb;
		Calendar calendar = Calendar.getInstance(Locale.US);
		calendar.set(Calendar.HOUR_OF_DAY, 6);
		String text;
		for(int i = 0; i < 20; i++) {
			tb = (ToggleButton) View.inflate(SetScheduleActivity.this, R.layout.taketime_button, null);
			text = getTakeTimeText(calendar);
			tb.setText(text);
			tb.setTextOff(text);
			tb.setTextOn(text);
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			
			mGlTakeTime.addView(tb);
		}
	}
	
	private static String getTakeTimeText(Calendar calendar) {
		if(calendar == null) {
			return "";
		}
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int ampm = calendar.get(Calendar.AM_PM);
		return String.format(Locale.US, "%d:00\n%s", hour > 12 ? hour - 12: hour,
				ampm == Calendar.AM ? "AM" : "PM");
	}

	public void onOkClick(View v) {
		
	}
}
