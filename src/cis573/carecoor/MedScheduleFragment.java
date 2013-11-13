package cis573.carecoor;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cis573.carecoor.bean.Medicine;
import cis573.carecoor.bean.Schedule;
import cis573.carecoor.data.DataCenter;
import cis573.carecoor.data.MedicineCenter;

public class MedScheduleFragment extends Fragment {

	public static final String TAG = "MedScheduleFragment";
	
	private static final int REQUEST_NEW_SCHEDULE = 0;

	private Button mBtnNew;
	private ListView mLvSchedules;
	private ScheduleAdapter mAdapter = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.med_schdule_view, container, false);
		mBtnNew = (Button) view.findViewById(R.id.med_schedule_add_button);
		mBtnNew.setOnClickListener(onNewClick);
		mLvSchedules = (ListView) view.findViewById(R.id.med_schedule_list);
		mLvSchedules.setOnItemClickListener(onScheduleItemClick);
		mLvSchedules.setOnItemLongClickListener(onScheduleItemLongClick);
		TextView tvEmpty = (TextView) view.findViewById(R.id.med_schedule_empty);
		mLvSchedules.setEmptyView(tvEmpty);
		mAdapter = new ScheduleAdapter(getActivity());
		mLvSchedules.setAdapter(mAdapter);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdapter.setScheduleList(DataCenter.getSchedules(getActivity()));
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_NEW_SCHEDULE) {
			if(resultCode == Activity.RESULT_OK) {
				mAdapter.setScheduleList(DataCenter.getSchedules(getActivity()));
				mAdapter.notifyDataSetChanged();
			}
		}
	}
	
	private OnItemClickListener onScheduleItemClick = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
		}
	};
	
	private OnItemLongClickListener onScheduleItemLongClick = new OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			final Schedule item = (Schedule) parent.getItemAtPosition(position);
			new AlertDialog.Builder(getActivity())
			.setTitle(R.string.dialog_title_remove_schedule)
			.setMessage(R.string.msg_remove_schedule)
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					DataCenter.removeSchedule(getActivity(), item);
					mAdapter.setScheduleList(DataCenter.getSchedules(getActivity()));
					mAdapter.notifyDataSetChanged();
				}
			}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			}).show();
			return false;
		}
	};

	private OnClickListener onNewClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), ChooseMedActivity.class);
			startActivityForResult(intent, REQUEST_NEW_SCHEDULE);
		}
	};
	
	public static class ScheduleAdapter extends BaseAdapter {

		private Context mContext;
		private List<Schedule> mScheduleList;
		
		public ScheduleAdapter(Context context) {
			this.mContext = context;
		}

		public void setScheduleList(List<Schedule> scheduleList) {
			this.mScheduleList = scheduleList;
		}

		@Override
		public int getCount() {
			return mScheduleList != null ? mScheduleList.size() : 0;
		}

		@Override
		public Object getItem(int position) {
			return mScheduleList != null ? mScheduleList.get(position) : null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh;
			if(convertView == null) {
				convertView = View.inflate(mContext, R.layout.medicine_list_item, null);
				vh = new ViewHolder();
				vh.name = (TextView) convertView.findViewById(R.id.medlist_item_name);
				vh.image = (ImageView) convertView.findViewById(R.id.medlist_item_image);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			Medicine med = null;
			Schedule item = (Schedule) getItem(position);
			if(item != null) {
				med = item.getMedicine();
			}
			if(med != null) {
				vh.name.setText(med.getName());
				vh.image.setImageResource(MedicineCenter.getMedicineImageRes(mContext, med));
			}
			return convertView;
		}
		
		private static class ViewHolder {
			TextView name;
			ImageView image;
		}
	}
}
