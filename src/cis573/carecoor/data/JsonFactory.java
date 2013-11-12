package cis573.carecoor.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cis573.carecoor.bean.Medicine;

public class JsonFactory {

	public static final String TAG = "JsonFactory";
	
	public static Medicine parseMedicine(JSONObject json) {
		if(json == null) {
			return null;
		}
		Medicine item = new Medicine();
		item.setName(json.optString("name"));
		item.setDetailedName(json.optString("detailed_name"));
		item.setInstructions(json.optString("instructions"));
		item.setCapacity(json.optString("capacity"));
		item.setDose(json.optInt("dose"));
		item.setTimes(json.optInt("times"));
		item.setInterval(json.optInt("interval"));
		item.setDuration(json.optInt("duration"));
		return item;
	}
	
	public static List<Medicine> parseMedicineList(JSONArray array) {
		if(array == null) {
			return null;
		}
		final int size = array.length();
		List<Medicine> list = new ArrayList<Medicine>(size);
		Medicine item;
		for(int i = 0; i < size; i++) {
			item = parseMedicine(array.optJSONObject(i));
			if(item != null) {
				list.add(item);
			}
		}
		return list;
	}
}
