package com.example.statsd;

import java.io.Serializable;
import java.util.Date;

import android.text.format.Time;

public class Match implements Serializable {
	int id , over;
	String matchTime;
	String matchDate;
	public Match(int id, int over, String matchTime, String matchDate) {
		super();
		this.id = id;
		this.over = over;
		this.matchTime = matchTime;
		this.matchDate = matchDate;
	}
	public Match(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOver() {
		return over;
	}
	public void setOver(int over) {
		this.over = over;
	}
	public String getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	@Override
	public String toString() {
		return "Match [id=" + id + ", over=" + over + ", matchTime="
				+ matchTime + ", matchDate=" + matchDate + "]";
	}
	
}
