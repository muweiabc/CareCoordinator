package cis573.carecoor;

import android.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class AddDurationActivity extends Activity {
	
	private Button nextButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_duration);
		nextButton=(Button)findViewById(R.id.next2);
	}
	
	public void f(View v){
		Intent intent=new Intent(this,AddTimeActivity.class);
		startActivity(intent);
	}

}
