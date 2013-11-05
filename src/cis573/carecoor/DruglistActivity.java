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

public class DruglistActivity extends Activity {
	private ListView druglist;
	private ArrayAdapter<Drug> adapter;
	private ArrayList<Drug> drugs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_druglist);
		initDruglist();
		druglist=(ListView)findViewById(R.id.druginfo);
		adapter=new ArrayAdapter<Drug>(this,android.R.layout.simple_list_item_1,drugs);
		druglist.setAdapter(adapter);
	}
	
	public void initDruglist(){
		drugs=new ArrayList<Drug>();
		Drug drug=new Drug("amoxi", "cva", "dddd");
		drugs.add(drug);
		drug=new Drug("penncilin", "zzz", "ttt");
		drugs.add(drug);
	}
/*
	public void initDruglist(){
		drugs=new ArrayList<Drug>();
		String filename="drug.txt";
		File file=new File(filename);
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNext()){
				String line=scanner.nextLine();
				
				int index=line.indexOf(')');
				line=line.substring(index+2);
				
				index=line.indexOf('-');
				String name=line.substring(0, index);
				line=line.substring(index+1);
			
				index=line.indexOf(',');
				String usage=line.substring(0, index);
				line=line.substring(index+1);
				String content=line;
				Drug drug=new Drug(name, usage, content);
				drugs.add(drug);
			//System.out.println(name+"////////"+usage+"///"+content);
			}
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}


class Drug
{
	public String name;
	//public String form; //400mg tablets 
	public String usage; //1 tablet by mouth twice daily
	public String content;//180 tablets per bottle
	public Drug(String n,String u,String c){
		name=n;
		//form=f;
		usage=u;
		content=c;	
	}
	public String toString(){
		return name+usage;
	}
}

