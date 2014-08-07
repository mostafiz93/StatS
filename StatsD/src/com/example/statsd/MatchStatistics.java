package com.example.statsd;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchStatistics extends Activity {
	
	ListView lvMatches;
	CustomAdapterMatch adapeter;
	DatabaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_statistics);
		
		lvMatches = (ListView) findViewById(R.id.lvMatches);
		dbHelper = new DatabaseHelper(this);
		
		
		ArrayList<Match> m = dbHelper.readAllMatches();
		if(m != null && m.size() > 0){
			adapeter = new CustomAdapterMatch(this, m);
			lvMatches.setAdapter(adapeter);
		} else 
			Toast.makeText(this, "No Data Found", 1).show(); 
		
		lvMatches.setOnItemClickListener(new OnItemClickListener() {
	         @Override
	         public void onItemClick(AdapterView<?> parent, android.view.View view,  int position, long id) {
	        	 //Toast.makeText(getApplicationContext(), "item clicked - " + position , Toast.LENGTH_LONG).show();
	        	 Match m = (Match) parent.getItemAtPosition(position);
	        	// Toast.makeText(getApplicationContext(), m.toString(), 1).show();
	        	 
	        	 
	        	 Intent in = new Intent(getApplicationContext(), SingleMatch.class);
	        	 in.putExtra("currentMatch",m);
	     		 startActivity(in);
	         }
	    });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	   MenuInflater inflater = getMenuInflater();
	   inflater.inflate(R.menu.main_menu, menu);
	   return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.add){
			Intent in = new Intent(this, AddMatch.class);
			startActivity(in);
			Toast.makeText(this, "button recognised as Add button", 1).show();
		}
		
		return true;
	}
}
