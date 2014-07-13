package com.example.statsd;

public class Player {
	int id, run, wickets, balls;
	double average, stRate;
	
	String Name, Address, Info;
	
	public Player(String name, String address, String info) {
		Name = name;
		Address = address;
		Info = info;
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", run=" + run + ", wickets=" + wickets
				+ ", balls=" + balls + ", average=" + average + ", stRate="
				+ stRate + ", Name=" + Name + ", Address=" + Address
				+ ", Info=" + Info + "]";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRun() {
		return run;
	}

	public void setRun(int run) {
		this.run = run;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getStRate() {
		return stRate;
	}

	public void setStRate(double stRate) {
		this.stRate = stRate;
	}

	

	

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getInfo() {
		return Info;
	}

	public void setInfo(String info) {
		Info = info;
	}
}
