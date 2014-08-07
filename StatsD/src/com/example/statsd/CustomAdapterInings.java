package com.example.statsd;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterInings extends ArrayAdapter<Inings> {
	
	Activity con;
	ArrayList<Inings> iningsList;
	
	public CustomAdapterInings(Context context, ArrayList<Inings> inings){
		super(context, R.layout.list_item_inings, inings);
		
		this.con = (Activity) context;
		this.iningsList = inings;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View v = null;
		
		if(convertView == null){
			LayoutInflater inflater = con.getLayoutInflater();
			v = inflater.inflate(R.layout.list_item_inings, null);
			
			TextView tvPlayerName = (TextView) v.findViewById(R.id.tvshowIningsPlayerName);
			TextView tvRun = (TextView) v.findViewById(R.id.tvShowIningsRun);
			TextView tvRunV = (TextView) v.findViewById(R.id.tvShowIningsRunV);
			TextView tvBall = (TextView) v.findViewById(R.id.tvShowIningsBall);
			TextView tvBallV = (TextView) v.findViewById(R.id.tvShowIningsBallV);
			TextView tvWicket = (TextView) v.findViewById(R.id.tvShowIningsWicket);
			TextView tvWicketV = (TextView) v.findViewById(R.id.tvShowIningsWicketV);
				
			Inings in = iningsList.get(position);
		Log.d("tag_customAdapterInings", in.toString());
			DatabaseHelper dbHelper = new DatabaseHelper(getContext());   // may cause error due to context
			
			String s = dbHelper.readPlayerNameWithIningsID(in.id);
			
			tvPlayerName.setText("Player Name : " + s);
			tvRun.setText("Run = ");
			tvRunV.setText(String.valueOf(in.getRuns()));
			tvBall.setText("Balls Played = ");
			tvBallV.setText(String.valueOf(in.getBalls()));
			tvWicket.setText("Wicket Earned = ");
			tvWicketV.setText(String.valueOf(in.getWickets()));
			
		} else 
			v = convertView;
		
		return v;
	}
}
