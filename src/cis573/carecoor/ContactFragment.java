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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import cis573.carecoor.adapter.ContactAdapter;
import cis573.carecoor.bean.Contact;
import cis573.carecoor.utils.FileKit;

public class ContactFragment extends Fragment {

	public static final String TAG = "ContactFragment";
	
	private Button mBtnAdd;
	private Button mBtnImport;
	private ExpandableListView mListView;
	private ContactAdapter mAdapter;
	private AlertDialog mAddContactDialog;
	
	private List<Contact> mUserContacts; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUserContacts = FileKit.readUserContacts(getActivity());
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
		mAdapter = new ContactAdapter(getActivity());
		addUsefulContacts();
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
	
	private void addUsefulContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact("Penn Nursing", "215-898-5074"));
		contacts.add(new Contact("University of Pennsylvania", "215-898-5000"));
		mAdapter.setContactList1(contacts);
		mAdapter.notifyDataSetChanged();
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
				FileKit.saveUserContacts(getActivity(), mUserContacts);
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
}
