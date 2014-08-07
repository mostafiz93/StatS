package com.example.statsd;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddMatch extends Activity {
	
	EditText etOver;
	DatePicker dpDate;
	TimePicker tpTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_match);
		
		etOver = (EditText) findViewById(R.id.etOver);
		dpDate = (DatePicker) findViewById(R.id.dpDate);
		tpTime = (TimePicker) findViewById(R.id.tpTime);
	}
	
	public void submit(View v){
		Match m = new Match();
		m.over = Integer.parseInt(etOver.getText().toString());
		m.matchDate = String.valueOf(dpDate.getDayOfMonth()) + "/"
				+ String.valueOf(dpDate.getMonth()) + "/"
				+ String.valueOf(dpDate.getYear());
		m.matchTime = String.valueOf(tpTime.getCurrentHour()) + ":"
				+ String.valueOf(tpTime.getCurrentMinute());
		
		DatabaseHelper db = new DatabaseHelper(this);
		long inserted = db.insertMatch(m);
		if(inserted > 0){
			Toast.makeText(this, "One Match Inserted", 1).show();
		}
		else 
			Toast.makeText(this, "Error Occured", 1).show();
	}
}
