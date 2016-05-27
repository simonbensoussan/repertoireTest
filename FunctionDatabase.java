package Projets;
/**
 * class comprenant des fonctions permettant de manipuler les objets de type swing(JTable,JTextField,...)
 * cette a 2 constructeur un par default et un prenant le nom de la classe Sqlite. */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

public class FunctionDatabase extends SqliteContact{
	
	/**CLASS :
	 * METHOD LIKE : fillCombo,fillTable,emptyField ... */

	/**
	 * create table using DefaultTableModel:
	 * DefaultModel: DefaultTableModel model = new DefaultTableModel();
	 * create column:
	 * add column:
	   model.addColumn("Name");				
	   model.addColumn("Mail");
	 * JTable table = new JTable(model); 
	 * OR : 
	 * table.setModel(model);
	 * 
	 */
//************************************************************************************************************************************		 	
	/**
	 *  addRow on the table:
	 *  WORK !!!
	
	DefaultTableModel model =(DefaultTableModel) table.getModel();
		//add row(ligne)
	    model.addRow(new Object[] { name.getText(),surname.getText(), mail.getText(),telephone.getText()});
	    emptyField(); */
	
	public static void emptyField(JTextField[]field_name)
	{
		for (JTextField field : field_name) {
			field.setText(null);
		}
	}
	
	/**
	 * fillTable
	 */
	public static  void fillTable(JTable table_name) 
	{
		try {
			DefaultTableModel model =(DefaultTableModel) table_name .getModel();
			model.setRowCount(0);
			ArrayList<Contact>MesContacts = new ArrayList<>();
			MesContacts = SqliteContact.getContactList();
			for(Contact item : MesContacts) {
			
					model.addRow(new Object[]{
							item.getSociety(),
							item.getName(),
							item.getSurName(),
							item.getMail(),
							item.getTelephone(),
						});
		
			}
			System.out.println("OK- [SYSTEM] - "+ MesContacts );
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
	
}
	public  static JComboBox fillCombo(JComboBox combo_name)
	{
		try {
			combo_name.removeAllItems();
			
			ArrayList<Contact>MesSocieties = new ArrayList<>();
			MesSocieties = SqliteContact.getContactList();
			for(Contact item : MesSocieties) {
				combo_name.addItem(	item.getSociety());
		
			}
			System.out.println("OK- [COMBOBOX SOCIETIES] - "+ MesSocieties );
			return combo_name;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}



public  static JComboBox fillComboSector(JComboBox combo_name)
{
	try {
	
			combo_name.removeAllItems();
			ArrayList<Sector>MesSectors = new ArrayList<>();
			MesSectors = SqliteSector.getSectorList();
			for(Sector item : MesSectors) {
				combo_name.addItem(item.getLibeller());	
			}
			System.out.println("OK- [COMBOBOX SECTORS] - "+ MesSectors );
			return combo_name;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

}

public  static void fillComboSociety(JComboBox combo_name) throws SQLException
{
	combo_name.removeAllItems();
	ResultSet rs = SqliteSector.getElements();
while (rs.next())
{		String string = rs.getObject("libeller").toString();
		combo_name.addItem(string);
	}
}

}