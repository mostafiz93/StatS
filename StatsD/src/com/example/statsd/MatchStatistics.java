package com.example.statsd;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MatchStatistics extends Activity {
	
	ListView lvMatches;
	CustomAdapterMatch adapeter;
	DatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_statistics);
		
		lvMatches = (ListView) findViewById(R.id.listView1);
		dbHelper = new DatabaseHelper(this);
		
		
		ArrayList<Match> m = dbHelper.readAllMatches();
		if(m != null && m.size() > 0){
			adapeter = new CustomAdapterMatch(this, m);
			lvMatches.setAdapter(adapeter);
		} else 
			Toast.makeText(this, "No Data Found", 1).show(); 
	}
}
