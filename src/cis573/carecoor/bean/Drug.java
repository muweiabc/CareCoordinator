package cis573.carecoor.bean;

import java.util.ArrayList;

public class Drug
{
	public static ArrayList<Drug> getDrugs(){
		ArrayList<Drug> list=new ArrayList<Drug>();
		Drug d=new Drug("Metformin HCl","1000mg tablets","1 tablet by mouth daily","90 tablets per bottle");
		list.add(d);
		d=new Drug("Ibuprofen","400mg tablets" ,"1 tablet by mouth twice daily", "180 tablets per bottle");
		list.add(d);
		return list;
	}
	private String name;
	private String form; //400mg tablets 
	private String usage; //1 tablet by mouth twice daily
	private String content;//180 tablets per bottle
	
	public Drug(String n,String f,String u,String c){
		name=n;
		form=f;
		usage=u;
		content=c;	
	}
	public String toString(){
		return name+" "+usage;
	}
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
