package cis573.carecoor.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import android.content.Context;
import cis573.carecoor.bean.Contact;

public class FileKit {
	
	public static final String TAG = "FileKit";
	
	private static final String FILENAME_USER_CONTACTS = "user_contacts";

	public static void saveUserContacts(Context context, List<Contact> list) {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME_USER_CONTACTS,
					Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
		} catch (Exception e) {
			Logger.e(TAG, e.getMessage(), e);
		} finally {
			try {
				if(oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				Logger.e(TAG, e.getMessage(), e);
			}
		}
	}
	
	public static List<Contact> readUserContacts(Context context) {
		List<Contact> list = null;
		ObjectInputStream ois = null;
		try {
			FileInputStream fis = context.openFileInput(FILENAME_USER_CONTACTS);
			ois = new ObjectInputStream(fis);
			list = (List<Contact>) ois.readObject();
		} catch (Exception e) {
			Logger.e(TAG, e.getMessage(), e);
		} finally {
			try {
				if(ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				Logger.e(TAG, e.getMessage(), e);
			}
		}
		return list;
	}
}
