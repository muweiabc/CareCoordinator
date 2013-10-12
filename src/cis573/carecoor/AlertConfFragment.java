package cis573.carecoor;

import cis573.carecoor.utils.PreferenceUtil;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class AlertConfFragment extends Fragment implements OnPhoneChangedListener {

	private View mPrimaryNumView;
	private View mSecondaryNumView;
	private TextView mTvPrimaryNum;
	private TextView mTvSecondaryNum;
	private CheckBox mCbCall;
	private CheckBox mCbText;
	private PhoneDialogFragment mPrimaryPhoneDialog;
	private PhoneDialogFragment mSecondaryPhoneDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPrimaryPhoneDialog = PhoneDialogFragment.newInstance(PhoneDialogFragment.PRIMARY_PHONE);
		mSecondaryPhoneDialog = PhoneDialogFragment.newInstance(PhoneDialogFragment.SECONDARY_PHONE);
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
		mTvPrimaryNum.setText(PreferenceUtil.getPrimaryAlertNumber(getActivity()));
		mTvSecondaryNum = (TextView) view.findViewById(R.id.alert_conf_secondary_text);
		mTvSecondaryNum.setText(PreferenceUtil.getSecondaryAlertNumber(getActivity()));
		mCbCall = (CheckBox) view.findViewById(R.id.alert_call_cb);
		mCbCall.setChecked(PreferenceUtil.getEnableAlertCall(getActivity()));
		mCbCall.setOnCheckedChangeListener(mOnCheckedChanged);
		mCbText = (CheckBox) view.findViewById(R.id.alert_text_cb);
		mCbText.setChecked(PreferenceUtil.getEnableAlertText(getActivity()));
		mCbText.setOnCheckedChangeListener(mOnCheckedChanged);
		return view;
	}
	
	private OnClickListener mOnNumsClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.alert_conf_primary:
				mPrimaryPhoneDialog.setTargetFragment(AlertConfFragment.this, 0);
				mPrimaryPhoneDialog.show(getFragmentManager(), "primary");
				break;
			case R.id.alert_conf_secondary:
				mSecondaryPhoneDialog.setTargetFragment(AlertConfFragment.this, 0);
				mSecondaryPhoneDialog.show(getFragmentManager(), "secondary");
				break;
			}
		}
	};
	
	public static class PhoneDialogFragment extends DialogFragment {

		public static final int PRIMARY_PHONE = 0;
		public static final int SECONDARY_PHONE = 1;
		
		private EditText mEtPhone;
		private int mType;
		
		public static PhoneDialogFragment newInstance(int type) {
			PhoneDialogFragment f = new PhoneDialogFragment();
			Bundle args = new Bundle();
			args.putInt("type", type);
			f.setArguments(args);
			return f;
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			Bundle args = getArguments();
			if(args != null) {
				mType = args.getInt("type");
			}
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			View view = View.inflate(getActivity(), R.layout.dialog_phone_num, null);
			mEtPhone = (EditText) view;
			String title = "";
			if(mType == PRIMARY_PHONE) {
				title = getActivity().getString(R.string.alertconf_primary_num);
			} else if(mType == SECONDARY_PHONE) {
				title = getActivity().getString(R.string.alertconf_secondary_num);
			}
			return new AlertDialog.Builder(getActivity()).setView(mEtPhone)
				.setTitle(title)
				.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String number = mEtPhone.getText().toString();
						OnPhoneChangedListener listener = (OnPhoneChangedListener) getTargetFragment();
						if(listener != null) {
							if(mType == PRIMARY_PHONE) {
								listener.onPrimaryNumChanged(number);
							} else if(mType == SECONDARY_PHONE) {
								listener.onSecondaryNumChanged(number);
							}
						}
					}
				}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				}).create();
		}
	}

	@Override
	public void onPrimaryNumChanged(String number) {
		PreferenceUtil.savePrimaryAlertNumber(getActivity(), number);
		mTvPrimaryNum.setText(number);
	}

	@Override
	public void onSecondaryNumChanged(String number) {
		PreferenceUtil.saveSecondaryAlertNumber(getActivity(), number);
		mTvSecondaryNum.setText(number);
	}
	
	private OnCheckedChangeListener mOnCheckedChanged = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.alert_call_cb:
				PreferenceUtil.saveEnableAlertCall(getActivity(), isChecked);
				break;
			case R.id.alert_text_cb:
				PreferenceUtil.saveEnableAlertText(getActivity(), isChecked);
				break;
			}
		}
	};
}
