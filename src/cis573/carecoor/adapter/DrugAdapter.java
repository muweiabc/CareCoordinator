package cis573.carecoor.adapter;

import cis573.carecoor.bean.Drug;
import android.R;
import android.content.Context;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class DrugAdapter extends ArrayAdapter<Drug> {
	
	private Context mContext=getContext();
	public DrugAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//View view=View.inflate(mContext, resource, root)
		///View.inflate(mContext, R.layout., parent);
		//return convertView;
		return super.getView(position, convertView, parent);
	}

	
}
