package com.example.statsd;

public class Inings {
	int id, balls, runs, wickets;
	String Date;
	public Inings(int id, int bowls, int runs, int wickets, String date) {
		super();
		this.id = id;
		this.balls = bowls;
		this.runs = runs;
		this.wickets = wickets;
		Date = date;
	}
	public Inings(){
		//empty constructor
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBalls() {
		return balls;
	}
	public void setBalls(int bowls) {
		this.balls = bowls;
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
		return "Inings [id=" + id + ", balls=" + balls + ", runs=" + runs
				+ ", wickets=" + wickets + ", Date=" + Date + "]";
	}
	
	
}
