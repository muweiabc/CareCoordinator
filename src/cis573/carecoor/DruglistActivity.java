package cis573.carecoor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cis573.carecoor.bean.*;

public class DruglistActivity extends Activity {
	private ListView druglist;
	private ArrayAdapter<Drug> adapter;
	private ArrayList<Drug> drugs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_druglist);
		drugs=Drug.getDrugs();
		druglist=(ListView)findViewById(R.id.druginfo);
		adapter=new ArrayAdapter<Drug>(this,android.R.layout.simple_list_item_1,drugs);
		druglist.setAdapter(adapter);
	}
}




