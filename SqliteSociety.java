package Projets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SqliteSociety extends NewSociety{
	public static Connection conn = null;
	public static ResultSet rs = null;
	
	//connection
	public static Connection dbConnector()
	{	
		//singleton
		if(conn == null)
		{
			
		try {
			Class.forName("org.sqlite.JDBC");
			  String dbURL = "jdbc:sqlite:C://Users//simon_bens//workspace//Projet_BTS//ProjetTest.sqlite"  ;// mettre'\\'
          // LYCEE :	  String dbURL = "jdbc:sqlite:P://Mes documents//Workspace//ProjetBTS//TestProjet.sqlite"; 	// mettre'\\'
            conn = DriverManager.getConnection(dbURL);
         //   JOptionPane.showMessageDialog(null,  "Connection ...");
           
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			//return null;
		}
		}
		 return conn;
		
	}
	
//******************************************************************************************************************************	
	
	//insert Society
	public static void insertSociety(String Name ,String Adresse  ,  String City ,  int CA ,  int Code_city , int Nombre_Employee , int sector_id  ,String Web_site)
	{
		try {
			PreparedStatement prst = conn.prepareStatement( "insert into Society ( Name , Adresse  ,  City ,  CA ,  Code_city ,Nombre_Employee , sector_id  ,Web_site ) values(?,?,?,?,?,?,?,?)");
			prst.setString(1,Name );
			prst.setString(2,Adresse );
			prst.setString(3,City );
			prst.setInt(4,CA );
			prst.setInt(5,Code_city );
			prst.setInt(6,Nombre_Employee );
			prst.setInt(7,sector_id );
			prst.setString(8,Web_site );
			prst.executeUpdate();
			JOptionPane.showMessageDialog(null,  "Data Society insert ...");
			prst.close();
			
		} catch (Exception e) {
			System.out.println(e);
		JOptionPane.showMessageDialog(null, e);
		
		}
	}
	//******************************************************************************************************************************	
	
		//delete
		public static void delete(Object Name)
		{
			try {
				String query ="delete from Society where Name = ?";
				PreparedStatement prst = conn.prepareStatement(query);
				prst.setObject(1,Name);
				prst.executeUpdate();
				JOptionPane.showMessageDialog(null,  "Data  Society Deleted");
				prst.close();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, e);
			
			}
	}
		
	//******************************************************************************************************************************
		
		//update
		public static void update(String Name ,String Adresse  ,  String City ,  int CA ,  int Code_city , int Nombre_Employee , int sector_id  ,String Web_site)
		{
			try {
				PreparedStatement prst = conn.prepareStatement("update Society set Name = ? Adresse  = ? City =? CA = ?Code_city =?Nombre_Employee = ? sector_id  =? Web_site= ? where Name = ?");
				prst.setString(1,Name );
				prst.setString(2,Adresse );
				prst.setString(3,City );
				prst.setInt(4,CA );
				prst.setInt(5,Code_city );
				prst.setInt(6,Nombre_Employee );
				prst.setInt(7,sector_id );
				prst.setString(8,Web_site );
				prst.executeUpdate();
				JOptionPane.showMessageDialog(null,  "Data insert...");
				prst.close();
				
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, e);
			
			}
		}
		
	//******************************************************************************************************************************	
		
		//getElement
		public static ResultSet getElements(String query)
		{
			try {	
				PreparedStatement prst = conn.prepareStatement(query);
				ResultSet rs = prst.executeQuery();	
				return rs;
			}
				
				catch (Exception e) {	
				 JOptionPane.showMessageDialog(null, e);
				 return null;
			}
			
		}
		

	public static ArrayList<Society> getSocietyList(){
		ArrayList<Society> society_arraylist = new ArrayList<Society>();	
		
		String sql = "SELECT * FROM Society";
		
		try {
			Society soc;
			PreparedStatement prst =conn.prepareStatement(sql);
			ResultSet rs =prst.executeQuery();
			
			while(rs.next()){
				soc = new Society(
						rs.getString("name"),
						rs.getString("Adresse"),
						rs.getString("city"),
						rs.getInt("cA "),
						rs.getInt("code_city"),
						rs.getInt("nombre_Employee"),
						rs.getInt("sectorID"),
						rs.getString("web_site")
						);
				society_arraylist.add(soc);
			}
			rs.close();
			prst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return society_arraylist ;
		
	}
	
	
}
