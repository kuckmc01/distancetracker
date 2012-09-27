package edu.uc.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashactivity);
		
		Button  btnskipTutorial = (Button) findViewById(R.id.btnskiptutorial);
		btnskipTutorial.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), StartGPSActivity.class);
				startActivityForResult(intent, 0);
				
			}
		});
	}
	
}
