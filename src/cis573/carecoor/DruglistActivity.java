package cis573.carecoor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import cis573.carecoor.bean.*;

public class DruglistActivity extends Activity {
	private ListView druglist;
	private ArrayAdapter<Drug> adapter;
	private ArrayList<Drug> drugs;
	private Button addDrugButton;
	private AlertDialog newdrugDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_druglist);
		drugs=Drug.getDrugs();
		
		druglist=(ListView)findViewById(R.id.druginfo);
		addDrugButton=(Button)findViewById(R.id.btnadd);
		
		adapter=new ArrayAdapter<Drug>(this,android.R.layout.simple_list_item_1,drugs);
		druglist.setAdapter(adapter);
		addDrugButton.setOnClickListener(ls);
		
		initdialog();
		
	}
	private OnClickListener ls=new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			newdrugDialog.show();
			
		}
		
	};
	
	private void initdialog(){
		View view=View.inflate(this,R.layout.dialog_add_drug, null);
		EditText nameEditText=(EditText)view.findViewById(R.id.drug_name_edittext);
		EditText formEditText=(EditText)view.findViewById(R.id.drug_form_edittext);
		EditText usageEditText=(EditText)view.findViewById(R.id.drug_usage_edittext);
		EditText contentEditText=(EditText)view.findViewById(R.id.drug_content_edittext);
		newdrugDialog=new AlertDialog.Builder(this).setTitle("Add new drug")
				.setView(view).create();
				//setPositiveButton(text, listener)
	}
}




