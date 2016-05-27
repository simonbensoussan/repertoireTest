package Projets;

import java.util.ArrayList;

public class Society {
	
	private int ID;
	private String Name;
	private String Adress;
	private String City;
   private int Code_city;
	private int CA;
	private int Nombre_Employee;
	private int  SectorID;
	private String Web_site;
	
	
	public Society(  String name, String adress, String city, int cA, int code_city,int nombre_Employee, int sectorID, String web_site) {
		
	
		Web_site = web_site;
		Code_city =code_city;
		Name = name;
		Adress = adress;
		City = city;
		CA = cA;
		Nombre_Employee = nombre_Employee;
		SectorID = sectorID;
		
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAdress() {
		return Adress;
	}
	public void setAdress(String adress) {
		Adress = adress;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public int getCA() {
		return CA;
	}
	public void setCA(int cA) {
		CA = cA;
	}
	public int getNombre_Employee() {
		return Nombre_Employee;
	}
	public void setNombre_Employee(int nombre_Employee) {
		Nombre_Employee = nombre_Employee;
	}
	public int getSector() {
		return SectorID;
	}
	public void setSector(int sectorID) {
		SectorID = sectorID;
	}
	public int getCode_city() {
		return Code_city;
	}
	public void setCode_city(int code_city) {
		Code_city = code_city;
	}
	public String getWeb_site() {
		return Web_site;
	}
	public void setWeb_site(String web_site) {
		Web_site = web_site;
	}

	@Override
	public String toString() {
		return String
				.format("Society [ID=%s, Name=%s, Adress=%s, City=%s, Code_city=%s, CA=%s, Nombre_Employee=%s, Sector=%s, Web_site=%s]",
						ID, Name, Adress, City, Code_city, CA, Nombre_Employee,
						SectorID, Web_site);
	}
	
	

}

