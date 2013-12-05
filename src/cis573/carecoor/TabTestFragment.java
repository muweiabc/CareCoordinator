package cis573.carecoor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

public class TabTestFragment extends Fragment {

	public static final String TAG = "TabTestFragment";
	
	private TabHost mTabHost;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab_test_fragment, container, false);
		mTabHost = (TabHost) view;
		mTabHost.setup();
		mTabHost.addTab(mTabHost.newTabSpec("tab_schedule")
				.setIndicator("Schedule", getResources().getDrawable(R.drawable.icon_schedule))
				.setContent(R.id.tab1));
		mTabHost.addTab(mTabHost.newTabSpec("tab_history")
				.setIndicator("History", getResources().getDrawable(R.drawable.icon_history)).setContent(R.id.tab2));
		return view;
	}
}
