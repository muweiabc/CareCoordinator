package cis573.carecoor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class AlertConfFragment extends Fragment {

	private View mPrimaryNumView;
	private View mSecondaryNumView;
	private TextView mTvPrimaryNum;
	private TextView mTvSecondaryNum;
	private CheckBox mCbCall;
	private CheckBox mCbText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.alert_conf_fragment, container, false);
		mPrimaryNumView = view.findViewById(R.id.alert_conf_primary);
		mPrimaryNumView.setOnClickListener(mOnNumsClick);
		mSecondaryNumView = view.findViewById(R.id.alert_conf_secondary);
		mSecondaryNumView.setOnClickListener(mOnNumsClick);
		mTvPrimaryNum = (TextView) view.findViewById(R.id.alert_conf_primary_text);
		mTvSecondaryNum = (TextView) view.findViewById(R.id.alert_conf_secondary_text);
		mCbCall = (CheckBox) view.findViewById(R.id.alert_call_cb);
		mCbText = (CheckBox) view.findViewById(R.id.alert_text_cb);
		return view;
	}
	
	private OnClickListener mOnNumsClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.alert_call_cb:
				
				break;
			case R.id.alert_text_cb:
				
				break;
			}
		}
	};
}
