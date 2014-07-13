package com.example.statsd;

public class Inings {
	int id, bowls, runs, wickets;
	String Date;
	public Inings(int id, int bowls, int runs, int wickets, String date) {
		super();
		this.id = id;
		this.bowls = bowls;
		this.runs = runs;
		this.wickets = wickets;
		Date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBowls() {
		return bowls;
	}
	public void setBowls(int bowls) {
		this.bowls = bowls;
	}
	public int getRuns() {
		return runs;
	}
	public void setRuns(int runs) {
		this.runs = runs;
	}
	public int getWickets() {
		return wickets;
	}
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	@Override
	public String toString() {
		return "Inings [id=" + id + ", bowls=" + bowls + ", runs=" + runs
				+ ", wickets=" + wickets + ", Date=" + Date + "]";
	}
	
	
}
