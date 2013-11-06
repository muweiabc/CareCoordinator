package cis573.carecoor.adapter;

import java.util.ArrayList;

import cis573.carecoor.bean.Drug;
import android.R;
import android.content.Context;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class DrugAdapter extends ArrayAdapter<Drug> {
	
	private Context mContext=getContext();
	public DrugAdapter(Context context, int textViewResourceId, ArrayList<Drug> items) {
		super(context, textViewResourceId, items);
		
		// TODO Auto-generated constructor stub
	}
/*
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//View view=View.inflate(mContext, resource, root)
		///View.inflate(mContext, R.layout., parent);
		//return convertView;
		return super.getView(position, convertView, parent);
		
		View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, null);
        }

        Drug item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            TextView itemView = (TextView) view.findViewById(R.id.ItemView);
            if (itemView != null) {
                // do whatever you want with your string and long
                itemView.setText(String.format("%s %d", item.reason, item.long_val));
            }
         }

        return view;
	}*/

	
}
