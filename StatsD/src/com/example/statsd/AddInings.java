package com.example.statsd;

import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddInings extends Activity {

	EditText etBalls;
	EditText etRUns;
	EditText etWickets;
	TextView tvIningsFor;
	AutoCompleteTextView autoCom;
	String playerNames[];
	Match m;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_inings);
		
		etBalls = (EditText) findViewById(R.id.etIningsBals);
		etRUns = (EditText) findViewById(R.id.etIningsRUns);
		etWickets = (EditText) findViewById(R.id.etIningsWickets);
		
		Intent in = getIntent();
		m = (Match) in.getSerializableExtra("currentMatch");
		tvIningsFor = (TextView) findViewById(R.id.tvIningsFor);
		tvIningsFor.setText(m.getMatchDate());
		
		DatabaseHelper dbHelper = new DatabaseHelper(this);
		List<String> plrs = dbHelper.readAllPlayerNames();
		playerNames = new String[plrs.size()];
		for(int i=0; i<plrs.size(); i++)playerNames[i] = plrs.get(i);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, playerNames);
		autoCom = (AutoCompleteTextView) findViewById(R.id.autoComPlayers);
		autoCom.setAdapter(adapter);
		
	}
	
	public void iningsSubmit(View v){
		
		//inings submission and inings id retrival
		Inings in = new Inings();
		in.balls = Integer.parseInt(etBalls.getText().toString());
		in.wickets = Integer.parseInt(etWickets.getText().toString());
		in.runs = Integer.parseInt(etRUns.getText().toString());
		
		DatabaseHelper dbHelper = new DatabaseHelper(this);
		
		String plrName = autoCom.getText().toString();
		int pId =dbHelper.readPlayerIdWithName(plrName);
		
		Log.d("tag_AddInings", "player id = "+pId);
		if(pId<0){
			Toast.makeText(this, "PLayer not found", 1).show();
			return;
		}
		
		int inserted = dbHelper.insertInings(in);
		
		if(inserted > 0)
			Toast.makeText(this, "inings inserted", 1).show();
		else 
			Toast.makeText(this, "Error occured", 1).show();
		
		Log.d("tag_AddInings","inings inserted with rowId "+inserted + " and iningsID " + inserted);
		
		int inId = inserted;
		int mId = m.id;
		
		
		long insertedInM = dbHelper.insertIiningsMatch(inId, mId);
		
		if(insertedInM > 0)
			Toast.makeText(this, "iningsMatch inserted", 1).show();
		else 
			Toast.makeText(this, "Error occured in iningsMatch", 1).show();
		
		
		
		Log.d("tag_AddInings", "Player id = "+pId);
		long insertPlrMatch = dbHelper.insertPlayerMatch(pId, mId);
		
		
		long insertIningsPlayer = dbHelper.insertIningsPlayer(inId, pId);
		
		long updated = dbHelper.updatePlayer(pId, in);
		
		Log.d("tag_AddInings", updated+ " rows updated");
		
		if(updated > 0)
			Toast.makeText(this, "Player Updated", 1).show();
		else 
			Toast.makeText(this, "Error occured in updating Player", 1).show();
		
		
	}
	
}
