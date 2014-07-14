package com.example.statsd;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterPlayerStats extends ArrayAdapter<Player> {
	
	Activity con;
	ArrayList<Player> playerList;
	
	public CustomAdapterPlayerStats(Context context, ArrayList<Player> players) {
		super(context, R.layout.list_item, players);
		// TODO Auto-generated constructor stub
		this.con = (Activity) context;
		this.playerList = players;
	}

	
	public View getView(int position, View convertView, ViewGroup parent){
		View v =null;
		if(convertView == null){
			LayoutInflater inflater = con.getLayoutInflater();
			v = inflater.inflate(R.layout.list_item, null);
			
			TextView txtName = (TextView) v.findViewById(R.id.tvName);
			TextView txtAddress  = (TextView) v.findViewById(R.id.tvAddress);
			TextView txtRun  = (TextView) v.findViewById(R.id.tvRun);
			TextView txtRunValue = (TextView) v.findViewById(R.id.tvRunValue);
			TextView txtWicket = (TextView) v.findViewById(R.id.tvWicket);
			TextView txtWicketValue  = (TextView) v.findViewById(R.id.tvWicketValue);
			TextView txtAverage  = (TextView) v.findViewById(R.id.tvAverage);
			TextView txtAverageValue  = (TextView) v.findViewById(R.id.tvAverageValue);
			TextView txtStRate  = (TextView) v.findViewById(R.id.tvStRate);
			TextView txtStRateValue  = (TextView) v.findViewById(R.id.tvStRateVlaue);
			
			
			Player plr = playerList.get(position);
			
			
			txtName.setText(plr.getName());
			txtAddress.setText(plr.getAddress());
			txtRun.setText("Run      : ");
			txtRunValue.setText(String.valueOf(plr.getRun()));
			txtWicket.setText(" Wickets :");
			txtWicketValue.setText(String.valueOf(plr.getWickets()));
			txtAverage.setText("Average : ");
			txtAverageValue.setText(String.valueOf(plr.getAverage()));
			txtStRate.setText("St. Rate :   ");
			txtStRateValue.setText(String.valueOf(plr.getStRate()));
			
			
			
		} else {
			v = convertView;
		}
		
		return v;
	}
	
}
