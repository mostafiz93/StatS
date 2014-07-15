package com.example.statsd;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapterMatch extends ArrayAdapter<Match> {
	
	Activity con;
	ArrayList<Match> matchList;
	
	public CustomAdapterMatch(Context context, ArrayList<Match> matches){
		super(context, R.layout.list_item_match, matches);
		
		this.con = (Activity) context;
		this.matchList = matches;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View v = null;
		
		if(convertView == null){
			LayoutInflater inflater = con.getLayoutInflater();
			v = inflater.inflate(R.layout.list_item_match, null);
			
			TextView tvID = (TextView) v.findViewById(R.id.tvID);
			TextView tvOver = (TextView) v.findViewById(R.id.tvOver);
			TextView tvOverValue = (TextView) v.findViewById(R.id.tvOverValue);
			TextView tvTime = (TextView) v.findViewById(R.id.tvTime);
			TextView tvDate = (TextView) v.findViewById(R.id.tvDate);
				
			Match m = matchList.get(position);
			
			tvID.setText(String.valueOf(m.getId()));
			tvOverValue.setText(String.valueOf(m.getOver()));
			tvOver.setText("Over : ");
			tvTime.setText(m.getMatchTime());
			tvDate.setText(m.getMatchDate());
			
			
		} else 
			v = convertView;
		
		return v;
	}

}
