package com.google.gcm;

import com.google.android.gcm.demo.logic.InstanceIdHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Button btn_register;
	private Button btn_unregister;
	private TextView txt_status;
	public static  EditText txt_token;
	private InstanceIdHelper mInstanceIdHelper;
	public static  Activity mainActivity1;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainActivity1=MainActivity.this;
		
		  btn_register=(Button)findViewById(R.id.btn_reg);
		  btn_unregister=(Button)findViewById(R.id.btn_unreg);
		  txt_status=(TextView)findViewById(R.id.txt_status);
		  txt_token=(EditText)findViewById(R.id.txt_reg_id);
		  mInstanceIdHelper=new InstanceIdHelper(MainActivity.this);
		
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		
		btn_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try
				{
					mInstanceIdHelper.getGcmTokenInBackground("117502150670");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		
		btn_unregister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mInstanceIdHelper.deleteGcmTokeInBackground("117502150670");
			}
		});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}


