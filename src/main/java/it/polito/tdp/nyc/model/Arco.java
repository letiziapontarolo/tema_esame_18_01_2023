package it.polito.tdp.nyc.model;

public class Arco {
	
	Location l1;
	Location l2;
	double peso;
	public Arco(Location l1, Location l2, double peso) {
		super();
		this.l1 = l1;
		this.l2 = l2;
		this.peso = peso;
	}
	public Location getL1() {
		return l1;
	}
	public void setL1(Location l1) {
		this.l1 = l1;
	}
	public Location getL2() {
		return l2;
	}
	public void setL2(Location l2) {
		this.l2 = l2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	

}
