package it.polito.tdp.nyc.model;


public class Location {
	
	String name;
	double lat;
	double lon;
	
	public Location(String name, double lat, double lon) {
		super();
		this.name = name;
		this.lat = lat;
		this.lon = lon;


	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}


	

}
