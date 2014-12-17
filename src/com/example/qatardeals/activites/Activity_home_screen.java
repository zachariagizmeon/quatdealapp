package com.example.qatardeals.activites;

import com.example.qatardeals.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Activity_home_screen extends Activity {
	TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen_page);
		title=(TextView)findViewById(R.id.textView1);
		title.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent home_next=new Intent(Activity_home_screen.this,Activity_recent_deals.class);
				startActivity(home_next);	// TODO Auto-generated method stub

			}
		});

	}

}
