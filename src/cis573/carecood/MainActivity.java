package cis573.carecood;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

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
			return new Fragment();
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
}
