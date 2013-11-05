package cis573.carecoor.data;

import java.util.List;

import android.content.Context;

import cis573.carecoor.bean.Contact;
import cis573.carecoor.utils.FileKit;

public class DataCenter {

	public static final String TAG = "DataCenter";
	
	private static final String FILENAME_USER_CONTACTS = "user_contacts";
	private static final String FILENAME_USEFUL_CONTACTS = "useful_contacts";
	
	private static List<Contact> mUsefulContacts = null;
	private static List<Contact> mUserContacts = null;
	
	public static List<Contact> getUsefulContacts(Context context) {
		if(mUsefulContacts == null) {
			mUsefulContacts = (List<Contact>) FileKit.readObject(context, FILENAME_USEFUL_CONTACTS);
		}
		return mUsefulContacts;
	}
	
	public static void setUsefulContacts(Context context, List<Contact> contacts) {
		if(contacts != null) {
			mUsefulContacts = contacts;
			FileKit.saveObject(context, FILENAME_USEFUL_CONTACTS, mUsefulContacts);
		}
	}
	
	public static List<Contact> getUserContacts(Context context) {
		if(mUserContacts == null) {
			mUserContacts = (List<Contact>) FileKit.readObject(context, FILENAME_USER_CONTACTS);
		}
		return mUserContacts;
	}
	
	public static void setUserContacts(Context context, List<Contact> contacts) {
		if(contacts != null) {
			mUserContacts = contacts;
			FileKit.saveObject(context, FILENAME_USER_CONTACTS, mUserContacts);
		}
	}
}
