package Projets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SqliteSector {
	
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
	//insert Sector
	public static void insertSector(String libeller )
	{
		try {
			PreparedStatement prst = conn.prepareStatement( "insert into Sector ( Libeller ) values(?)");
			prst.setString(1,libeller );
			prst.executeUpdate();
			JOptionPane.showMessageDialog(null,  "Data Sector insert ...");
			prst.close();
			
		} catch (Exception e) {
			System.out.println(e);
		JOptionPane.showMessageDialog(null, e);
		
		}
	}
	//******************************************************************************************************************************	
	
			//getElement
			public static ResultSet getElements()
			{
				String query  = "select * from sector";
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
			
			public static ArrayList<Sector> getSectorList(){
				
				ArrayList<Sector> sector_arraylist = new ArrayList<Sector>();	
				
				String sql = "SELECT * FROM SECTOR";
				
				try {
					
					Sector sec;
					PreparedStatement prst =conn.prepareStatement(sql);
					ResultSet rs =prst.executeQuery();
					
					while(rs.next()){
						sec = new Sector(
								rs.getInt("id"),
								rs.getString("Libeller")
								);
						sector_arraylist.add(sec);
					}
					rs.close();
					prst.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
					e.printStackTrace();
				}
				return sector_arraylist ;
			}
}
