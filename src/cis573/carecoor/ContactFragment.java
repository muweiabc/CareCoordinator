package cis573.carecoor;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import cis573.carecoor.adapter.ContactAdapter;
import cis573.carecoor.bean.Contact;
import cis573.carecoor.data.DataCenter;

public class ContactFragment extends Fragment {

	public static final String TAG = "ContactFragment";
	
	private Button mBtnAdd;
	private Button mBtnImport;
	private ExpandableListView mListView;
	private ContactAdapter mAdapter;
	private AlertDialog mAddContactDialog;
	
	private List<Contact> mUsefulContacts;
	private List<Contact> mUserContacts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUsefulContacts = DataCenter.getUsefulContacts(getActivity());
		mUserContacts = DataCenter.getUserContacts(getActivity());
		if(mUserContacts == null) {
			mUserContacts = new ArrayList<Contact>();
		}
		initAddContactDialog();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.contact_fragment, container, false);
		mBtnAdd = (Button) view.findViewById(R.id.contact_add_btn);
		mBtnImport = (Button) view.findViewById(R.id.contact_import_btn);
		mBtnAdd.setOnClickListener(onButtonClick);
		mBtnImport.setOnClickListener(onButtonClick);
		mListView = (ExpandableListView) view.findViewById(R.id.contact_listview);
		mListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
		});
		mListView.setOnItemLongClickListener(onLongClick);
		mAdapter = new ContactAdapter(getActivity());
		mAdapter.setContactList1(mUsefulContacts);
		mAdapter.setContactList2(mUserContacts);
		mListView.setAdapter(mAdapter);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		for(int i = 0; i < mAdapter.getGroupCount(); i++) {
			mListView.expandGroup(i);
		}
	}
	
	private OnClickListener onButtonClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			int id = v.getId();
			if(id == R.id.contact_add_btn) {
				mAddContactDialog.show();
			} else if(id == R.id.contact_import_btn) {
				
			}
		}
	};
	
	private OnItemLongClickListener onLongClick = new OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			long flatPosition = mListView.getExpandableListPosition(position);
			if(ExpandableListView.getPackedPositionType(flatPosition)
					== ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
				int groupPosition = ExpandableListView.getPackedPositionGroup(flatPosition);
	            if(groupPosition == 1) {
	            	int childPosition = ExpandableListView.getPackedPositionChild(flatPosition);
	            	showDeleteContactDialog(childPosition);
	            }
			}
			return true;
		}
	};
	
	private void initAddContactDialog() {
		View view = View.inflate(getActivity(), R.layout.dialog_add_contact, null);
		final EditText etName = (EditText) view.findViewById(R.id.contact_name_edittext);
		final EditText etPhone = (EditText) view.findViewById(R.id.contact_phone_edittext);
		mAddContactDialog = new AlertDialog.Builder(getActivity())
		.setTitle(R.string.dialog_add_contact_title)
		.setView(view)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String name = etName.getText().toString();
				String phone = etPhone.getText().toString();
				mUserContacts.add(new Contact(name, phone));
				DataCenter.setUserContacts(getActivity(), mUserContacts);
				mAdapter.setContactList2(mUserContacts);
				mAdapter.notifyDataSetChanged();
			}
		}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		}).create();
	}
	
	private void showDeleteContactDialog(final int position) {
		new AlertDialog.Builder(getActivity())
		.setTitle(R.string.dialog_delete_contact_title)
		.setMessage(R.string.dialog_delete_contact_msg)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mUserContacts.remove(position);
				DataCenter.setUserContacts(getActivity(), mUserContacts);
				mAdapter.setContactList2(mUserContacts);
				mAdapter.notifyDataSetChanged();
			}
		}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		}).show();
	}
}
