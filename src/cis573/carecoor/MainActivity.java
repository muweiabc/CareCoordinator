package cis573.carecoor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import cis573.carecoor.utils.Logger;
import cis573.carecoor.utils.PreferenceUtil;

public class MainActivity extends FragmentActivity {
	
	public static final String TAG = "MainActivity";

	private static final String[] PAGE_TITLES = {"Voice", "Contacts", "Friends", "Games", "More"}; 
	
	private Button mBtnAlert;
	private ViewPager mViewPager;
	private AlertDialog mAlertCfmDialog;
	private AlertDialog mAlertSecDialog;
	
	private MainPagerAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Logger.setDebug(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		initViews();
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		Logger.i(TAG, String.format("Back from call, request=%d, result=%d", arg0, arg1));
		mAlertSecDialog.show();
	}

	private void initViews() {
		mBtnAlert = (Button) findViewById(R.id.main_alert_btn);
		mBtnAlert.setOnClickListener(onAlertClick);
		mViewPager = (ViewPager) findViewById(R.id.main_pager);
		mAdapter = new MainPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mAlertCfmDialog = new AlertDialog.Builder(this)
		.setTitle(R.string.dialog_alert_cfm_title)
		.setMessage(R.string.dialog_alert_cfm)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				alertPrimary();
			}
		}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		}).create();
		mAlertSecDialog = new AlertDialog.Builder(this)
		.setTitle(R.string.dialog_alert_cfm_title)
		.setMessage(R.string.dialog_alert_secondary)
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		}).setNeutralButton(R.string.dialog_alert_primary_again, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				alertPrimary();
			}
		}).setNegativeButton(R.string.dialog_alert_secondary_again, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				alertSecondary();
			}
		}).create();
	}
	
	private void alertPrimary() {
		String number = PreferenceUtil.getPrimaryAlertNumber(this);
		doAlert(number);
	}
	
	private void alertSecondary() {
		String number = PreferenceUtil.getSecondaryAlertNumber(this);
		doAlert(number);
	}
	
	private void doAlert(String number) {
		boolean makeCall = PreferenceUtil.getEnableAlertCall(this);
		boolean makeText = PreferenceUtil.getEnableAlertText(this);
		
		if(makeCall) {
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + number));
			startActivityForResult(intent, 0);
		}
		
		if(makeText) {
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(number, null, "Test message from care coordinator", null, null);
		}
	}
	
	private OnClickListener onAlertClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mAlertCfmDialog.show();
		}
	};
	
	public static class MainPagerAdapter extends FragmentPagerAdapter {

		public MainPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment f;
			if(arg0 == 0) {	// Alert
				f = new AlertConfFragment();
			} else if(arg0 == 1) {	// Contact
				f = new ContactFragment();
			} else {
				f = DummyFragment.newInstance(PAGE_TITLES[arg0]);
			}
			return f;
		}

		@Override
		public int getCount() {
			return PAGE_TITLES.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return PAGE_TITLES[position];
		}
	}
	
	public static class DummyFragment extends Fragment {

		private String mTitle;
		
		public static DummyFragment newInstance(String title) {
			DummyFragment f = new DummyFragment();
			Bundle args = new Bundle();
			args.putString("title", title);
			f.setArguments(args);
			return f;
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			Bundle args = getArguments();
			if(args != null) {
				mTitle = args.getString("title");
			}
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.dummy_fragment, container, false);
			TextView tv = (TextView) view;
			if(mTitle != null) {
				tv.setText(mTitle);
			}
			return view;
		}
		
	}
}
