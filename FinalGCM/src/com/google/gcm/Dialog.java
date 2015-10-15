package com.google.gcm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Dialog extends Activity{
	
	private TextView _txt_message;
	private TextView _txt_cancel;
	
	@Override
	protected void onCreate(Bundle SavedInstance)
	{
		super.onCreate(SavedInstance);
		setContentView(R.layout.dialog_box);
		_txt_message=(TextView)findViewById(R.id.txt_message);
		_txt_cancel=(TextView)findViewById(R.id.btn_yes);
	}
	@Override
	protected void onResume()
	{
		super.onResume();
		_txt_message.setText(getIntent().getStringExtra("message"));
		
		_txt_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
				
			}
		});
	}

}
