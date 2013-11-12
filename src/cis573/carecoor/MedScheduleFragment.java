package cis573.carecoor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MedScheduleFragment extends Fragment {

	public static final String TAG = "MedScheduleFragment";
	
	private static final int REQUEST_NEW_SCHEDULE = 0;

	private Button mBtnNew;
	private ListView mLvSchedules;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.med_schdule_view, container, false);
		mBtnNew = (Button) view.findViewById(R.id.med_schedule_add_button);
		mBtnNew.setOnClickListener(onNewClick);
		mLvSchedules = (ListView) view.findViewById(R.id.med_schedule_list);
		TextView tvEmpty = (TextView) view.findViewById(R.id.med_schedule_empty);
		mLvSchedules.setEmptyView(tvEmpty);
		return view;
	}
	
	private OnClickListener onNewClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), ChooseMedActivity.class);
			startActivityForResult(intent, REQUEST_NEW_SCHEDULE);
		}
	};
}
