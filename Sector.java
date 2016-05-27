package Projets;

import java.util.ArrayList;

import javax.swing.JComboBox;

public class Sector {

	private  int id;
	private String libeller;
	public Sector( int id, String libeller) {
		this.id =id; 
		this.libeller = libeller;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getLibeller() {
		return libeller;
	}
	public void setLibeller(String libeller) {
		this.libeller = libeller;
	}

	@Override
	public String toString() {
		return String.format("Sector [id=%s, libeller=%s]", id, libeller);
	}
	
	
	

	
}
