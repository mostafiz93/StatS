package com.example.statsd;

public class Match {
	int id , over;
	String date;
	
	
	public Match(int id, int over, String date) {
		super();
		this.id = id;
		this.over = over;
		this.date = date;
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


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Match [id=" + id + ", over=" + over + ", date=" + date + "]";
	}
	
	
	
}
