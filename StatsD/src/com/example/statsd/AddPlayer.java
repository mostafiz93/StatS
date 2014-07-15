package com.example.statsd;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayer extends Activity {
	
	EditText etName, etAdd, etInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_player);
		
		etName = (EditText) findViewById(R.id.etName);
		etAdd = (EditText) findViewById(R.id.etAdd);
		etInfo = (EditText) findViewById(R.id.etInfo);
		
		
	}
	
	public void submit(View v){
		String Name = etName.getText().toString();
		String Address = etAdd.getText().toString();
		String Info = etInfo.getText().toString();
		
		Player pl = new Player(Name, Address, Info);
		
		pl.balls = pl.run =  pl.wickets =0;
		pl.stRate = pl.average =0;
		
		DatabaseHelper db = new DatabaseHelper(this);
		long inserted = db.insertPlayer(pl);
		if(inserted>0)
			Toast.makeText(this, "One value inserted", 1).show();
		else 
			Toast.makeText(this , "Error occurred", 1).show();
		
		//Toast.makeText(this, pl.toString(), 1).show();
	}
	 
}
