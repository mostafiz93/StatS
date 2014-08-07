package com.example.statsd;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleMatch extends Activity {
	
	CustomAdapterInings adapter;
	ListView lvInings;
	Match m;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_match);

		Intent i = getIntent();
		m = (Match)i.getSerializableExtra("currentMatch");
		
		TextView tvDate = (TextView) findViewById(R.id.tvDateAndTime);
		TextView tvOver = (TextView) findViewById(R.id.tvOver);
		
		tvDate.setText(m.getMatchDate() + " " + m.getMatchTime());
		tvOver.setText("" + m.getOver());
		
		
		lvInings = (ListView)findViewById(R.id.lvInigsOfMatch);
		
		
		
		DatabaseHelper dbHelper = new DatabaseHelper(this);
		ArrayList<Inings> ar = dbHelper.readAllIningsWithMatchId(m.id);
		
		Log.d("Tag_SingleMatch", "inings found = " + ar.size());
		if(ar.size() > 0){
			adapter = new CustomAdapterInings(this, ar);
			lvInings.setAdapter(adapter);
		} else {
			Log.d("Tag_SingleMatch","came to else");
			Toast.makeText(this, "No inings Found", 1).show();
		}
		
		Log.d("Tag_SingleMatch", m.toString());
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	   MenuInflater inflater = getMenuInflater();
	   inflater.inflate(R.menu.main_menu, menu);
	   return true;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.add){
			Intent in = new Intent(this, AddInings.class);
			in.putExtra("currentMatch", m);
			startActivity(in);
			Toast.makeText(this, "button recognised as Add button", 1).show();
		}
		
		return true;
	}
	
}
