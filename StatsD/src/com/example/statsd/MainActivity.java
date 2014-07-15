package com.example.statsd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	public void addPlayer(View v){
		Intent in = new Intent(this, AddPlayer.class);
		startActivity(in);
	}
	
	public void addMatch(View v){
		Intent in = new Intent(this, AddMatch.class);
		startActivity(in);
	}
	
	public void matchStatistics(View v){
		Intent in = new Intent(this, MatchStatistics.class);
		startActivity(in);
	}
	
	public void playerStatistics(View v){
		Intent in = new Intent(this, PlayerStatistics.class);
		startActivity(in);
	}
}
