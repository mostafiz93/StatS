package com.example.statsd;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerStatistics extends Activity {
	
	DatabaseHelper db;
	List<String> names;
	
	//list view
	ListView lvPlayer;
	
	//custom array adapter
	CustomAdapterPlayerStats adapter;
	
	int n;
	String nameArray[];
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_statistics);
		
		db  = new DatabaseHelper(this);
		lvPlayer = (ListView) findViewById(R.id.listView1);
		
		names =  db.readAllNames();
		n  = names.size();;
		int i;
		nameArray = new String[n];
		for(i=0;i<n;i++) 
			nameArray[i] = names.get(i);
		
		
		
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
				android.R.layout.simple_dropdown_item_1line, nameArray);
		
		AutoCompleteTextView autoTv = (AutoCompleteTextView) findViewById(R.id.actvChhose);
		
		autoTv.setAdapter(adapter);
		
	}
	
	public void showStatistics(View v){
		
		Toast.makeText(this, Integer.toString(n), 1).show();
		ArrayList<Player> players = db.readAllPlayers();
		
		if(players != null && players.size() > 0 ) {
			adapter = new CustomAdapterPlayerStats(this, players);
			lvPlayer.setAdapter(adapter);
		}
		
	}
}
