package cis573.carecoor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private static final String[] PAGE_TITLES = {"Voice", "Contacts", "Friends", "Games", "More"}; 
	
	private ViewPager mViewPager;
	
	private MainPagerAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		initViews();
	}

	private void initViews() {
		mViewPager = (ViewPager) findViewById(R.id.main_pager);
		mAdapter = new MainPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
	}
	
	public static class MainPagerAdapter extends FragmentPagerAdapter {

		public MainPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return DummyFragment.newInstance(PAGE_TITLES[arg0]);
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
