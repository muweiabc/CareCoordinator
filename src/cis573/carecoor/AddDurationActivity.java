package cis573.carecoor;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddDurationActivity extends Activity {

	private Button nextButton;
	private Button backButton;
	
	private final int code1=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_duration);
		nextButton=(Button)findViewById(R.id.next_duration);
		backButton=(Button)findViewById(R.id.back_duration);
		nextButton.setOnClickListener(listener);
		backButton.setOnClickListener(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_duration, menu);
		return true;
	}
	
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id=v.getId();
			if(id==R.id.next_duration){
				Intent intent=new Intent(AddDurationActivity.this,AddTimeActivity.class);
				startActivityForResult(intent,code1);
			}else
			if(id==R.id.back_duration){
				setResult(RESULT_CANCELED);
				finish();
			}
		}
	};
	
	@Override
	public void onActivityResult(int requestCode, int resultCode,Intent data){
		if (requestCode == code1) {
            if (resultCode == RESULT_OK) {
                // A contact was picked.  Here we will just display it
                // to the user.
            	Intent result=new Intent();
                setResult(RESULT_OK,result);
                finish();
            }
        }
        
	}
}
