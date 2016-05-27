package Projets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;



public class SqliteContact {
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
            JOptionPane.showMessageDialog(null,  "Connection ...");
           
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			//return null;
		}
		}
		 return conn;
		
	}
	
//******************************************************************************************************************************	
	
	//insert
	public static void insert(Object Society,Object Name,Object Surname,Object Mail,Object Telephone)
	{
		try {
			PreparedStatement prst = conn.prepareStatement("insert into Contact (Society,Name,Surname,Mail,Telephone) values(?,?,?,?,?)");
			prst.setObject(1, Society);
			prst.setObject(2, Name);
			prst.setObject(3, Surname);
			prst.setObject(4, Mail);
			prst.setObject(5, Telephone);
			prst.executeUpdate();
			JOptionPane.showMessageDialog(null,  "Data insert ...");
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
			String query ="delete from Contact where Name = ?";
			PreparedStatement prst = conn.prepareStatement(query);
			prst.setObject(1,Name);
			prst.executeUpdate();
			JOptionPane.showMessageDialog(null,  "Data Delete...");
			prst.close();
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e);
		
		}
}
	
//******************************************************************************************************************************
	
	//update
	public static void update(Object Name,Object Surname,Object Mail,Object Telephone, Object Society)
	{
		try {
			PreparedStatement prst = conn.prepareStatement("update Contact set Name=?,Surname=?,Mail=?,Telephone=? where Society=? ");
			prst.setObject(1, Name);
			prst.setObject(2, Surname);
			prst.setObject(3, Mail);
			prst.setObject(4, Telephone);
			prst.setObject(5, Society);
			prst.executeUpdate();
			JOptionPane.showMessageDialog(null,  "Data update ...");
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
	
//******************************************************************************************************************************	
	public static ArrayList<Contact> getContactList(){
		ArrayList<Contact> contact_arraylist = new ArrayList<Contact>();	
		
		String sql = "SELECT * FROM CONTACT";
		
		try {
			Contact cont;
			PreparedStatement prst =conn.prepareStatement(sql);
			ResultSet rs =prst.executeQuery();
			
			while(rs.next()){
				cont = new Contact(
						rs.getString("society"),
						rs.getString("name"),
						rs.getString("surName"),
						rs.getString("mail"),
						rs.getInt("telephone")
						);
				contact_arraylist.add(cont);
			}
			rs.close();
			prst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return contact_arraylist;
		
	}
//******************************************************************************************************************************
	//disconnection
	public static void dbDisconnect()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	//******************************************************************************************************************************
	
	
	
	
	
}
