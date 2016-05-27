package Projets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Test extends JFrame {

	private JPanel contentPane;
	
	private JFrame SocietyFrame;
	private JTable table;
	Contact contact;
	Connection connect;
	private JTextField tf_search;
	private JComboBox<Contact> comboSociety;
	private JTextField tf_name;
	private JTextField tf_mail;
	private JTextField tf_numTel;
	private JTextField tf_surname;
	private JTextField tf_society;
	
//======================================================================

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	

//======================================================================

	/**
	 * constructor
	 */
		public Test() {
			init();
			connect = SqliteContact.dbConnector();
			FunctionDatabase.fillCombo(comboSociety);
			searchFillTable();
		}

//======================================================================

/**
 *Function of the Application
 *  
 */
	public void emptyField()
	{
		 // FunctionTools work with emptyFiekd method, see if we make a abstract or interface class
		FunctionDatabase.emptyField(new JTextField[]{tf_society,tf_name,tf_surname,tf_mail,tf_numTel,tf_search});
	}
	
	//fill arraylist from database
	public void searchFillTable() 
	{
		FunctionDatabase.fillTable(table);		
	}
/*		public void showContact(){
		ArrayList<Contact> contact_list = SqliteContact.getContactList();
		DefaultTableModel table_model =(DefaultTableModel) table.getModel();
		
	}*/
	private void createContact() {
		//create new contact into DataBase
		contact = new Contact( tf_society.getText(),tf_name.getText(),tf_surname.getText(), tf_mail.getText(),Integer.parseInt(tf_numTel.getText()));
		SqliteContact.insert(contact.getSociety(),contact.getName(),contact.getSurName(),contact.getMail(),contact.getTelephone());
		System.out.println(("[ID-CONTACT CREATES : ]"  + tf_society.getText()+" "+ tf_name.getText()+" "+tf_surname.getText()+" "+ tf_mail.getText()+" "+Integer.parseInt(tf_numTel.getText())));
		emptyField();
	}

	private void DeleteData() {
		SqliteContact.delete(tf_name.getText());
		System.out.println((tf_society.getText()+" "+tf_name.getText()+" "+tf_surname.getText()+" "+ tf_mail.getText()+" "+Integer.parseInt(tf_numTel.getText())));
		searchFillTable();
		emptyField();
		 System.out.println("OK-[DATA DELETE]");
	}
	private void updateData() {
		SqliteContact.update( tf_name.getText(),tf_surname.getText(), tf_mail.getText(),Integer.parseInt(tf_numTel.getText()),tf_society.getText());
		System.out.println((tf_name.getText()+" "+tf_surname.getText()+" "+ tf_mail.getText()+" "+Integer.parseInt(tf_numTel.getText())));
		searchFillTable();
		emptyField();
	 System.out.println("OK-[DATA UPDATE]");
	}
	
	private void setRowToTextField() {
		//select row from JTable and set into JTextField
		  int row =  table.getSelectedRow();
		  tf_society.setText(table.getValueAt(row, 0).toString());
		  tf_name.setText(table.getValueAt(row, 1).toString());
		  tf_surname.setText(table.getValueAt(row, 2).toString());
		  tf_mail.setText(table.getValueAt(row, 3).toString());
		  tf_numTel.setText(table.getValueAt(row, 4).toString());
		 System.out.println("[ROW PRINT]-OK");
	}
	private void InternetConnection() {
		//check if connection to Internet available
			Socket socket = new Socket();
			InetSocketAddress adresse = new InetSocketAddress("www.google.com",80);  //adresse test 
			try {
				socket.connect(adresse,3000);
				System.out.println("TRUE-[CONNECTED ]");
			} catch (IOException e) {
				System.out.println("FALSE-[CONNECTED ]");
				e.printStackTrace();
			}
	}
	public  void openWebPage() throws IOException, URISyntaxException
	{
		// OPEN WEB PAGE FROM DESKTOP BROWSER
		String item_selected = comboSociety.getSelectedItem().toString();
		Desktop d = Desktop.getDesktop();
		switch (item_selected) {
		case  "lucien":
			d.browse(new URI("www.alcatel-lucent.com/fr"));
			break;
		case  "alcatel":
			d.browse(new URI("www.youtube.com"));
			break;
	
		default:
			Desktop def = Desktop.getDesktop();
			d.browse(new URI("www.google.com/fr"));
			break;
		}
	
	}
	
//==================================================================================================

	/**
	 * Create the frame.
	 */
	public void init() {
		setTitle("Contact Repersitory");	
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		setBounds(100, 100, 938, 540);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("CONTACT REPERSITORY");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(100, 149, 237));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 11, 923, 50);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setToolTipText("");
		scrollPane.setBounds(373, 126, 559, 207);
		getContentPane().add(scrollPane);
		
		//get column name on the frame:
		   DefaultTableModel model = new DefaultTableModel();
		  
		   table = new JTable(model);
		   table.addMouseListener(new MouseAdapter() {
		   @Override
			public void mouseClicked(MouseEvent arg0) {
			   			setRowToTextField();   	
			}
		});
		   model.addColumn("Society");
		   model.addColumn("Name");
		   model.addColumn("Surname");
		   model.addColumn("Mail");
		   model.addColumn("Telephone");
		  
		   scrollPane.setViewportView(table);
		   
		JButton btnSearch = new JButton("Display All");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//load data into JTable
				searchFillTable();
				emptyField();
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSearch.setBounds(754, 91, 109, 20);
		getContentPane().add(btnSearch);
		
		JLabel labelSearch = new JLabel("");
		labelSearch.setIcon(new ImageIcon("C:\\Users\\simon_bens\\Pictures\\circle-find-icon (24px).png"));
		labelSearch.setBounds(395, 88, 29, 27);
		getContentPane().add(labelSearch);
		
		JLabel lblNewLabel_1 = new JLabel("Developed by Simon CopyRight");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 474, 192, 27);
		getContentPane().add(lblNewLabel_1);
		
		tf_search = new JTextField();
		tf_search.setColumns(10);
		tf_search.setBounds(470, 92, 257, 20);
		getContentPane().add(tf_search);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSearch.setBounds(421, 88, 46, 27);
		getContentPane().add(lblSearch);
		
		//add 25/05
//==================== PANEL SOCIETY =================================================================================	
		
		JPanel panelSociety = new JPanel();
		panelSociety.setLayout(null);
		panelSociety.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Society", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		panelSociety.setBounds(373, 340, 294, 96);
		getContentPane().add(panelSociety);
		//FunctionDatabase.fillCombo(comboSociety);
		
		comboSociety = new JComboBox();
		comboSociety.setBounds(63, 22, 205, 20);
		panelSociety.add(comboSociety);
		

			
			JLabel lblTrier = new JLabel("Name : ");
			lblTrier.setBounds(10, 25, 63, 14);
			panelSociety.add(lblTrier);
			lblTrier.setFont(new Font("Tahoma", Font.BOLD, 11));
			
			JButton btnOpenWebPage = new JButton("Site WEB ");
			btnOpenWebPage.setIcon(new ImageIcon("C:\\Users\\simon_bens\\Pictures\\browser-icon.png"));
			btnOpenWebPage.setBounds(161, 54, 123, 31);
			panelSociety.add(btnOpenWebPage);
			btnOpenWebPage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				InternetConnection();
				try {
					openWebPage();
					System.out.println("OK-[WEB PAGE OPENED ]");
				} catch (IOException e) {
					System.out.println("BUG-[WEB PAGE OPENED ]");
					e.printStackTrace();
				} catch (URISyntaxException e) {
					System.out.println("BUG-[WEB PAGE OPENED ]");
					e.printStackTrace();
				}
				}	
			});
			btnOpenWebPage.setForeground(Color.BLUE);
			btnOpenWebPage.setFont(new Font("Tahoma", Font.BOLD, 12));
			
			JButton btnAddSociety = new JButton("New Society");
			btnAddSociety.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NewSociety new_soc;
					try {
						new_soc = new NewSociety();
						new_soc.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btnAddSociety.setIcon(new ImageIcon("C:\\Users\\simon_bens\\Pictures\\Factory-icon.png"));
			btnAddSociety.setForeground(new Color(255, 140, 0));
			btnAddSociety.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnAddSociety.setBounds(20, 53, 122, 32);
			panelSociety.add(btnAddSociety);

//==================== PANEL CATEGORY =======================================================================================
		
		JPanel panelCategory = new JPanel();
		panelCategory.setLayout(null);
		panelCategory.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Category", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		panelCategory.setBounds(677, 340, 235, 95);
		getContentPane().add(panelCategory);
		
		JLabel lblNomCategorie = new JLabel("Nom Categorie:");
		lblNomCategorie.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomCategorie.setBounds(10, 21, 93, 14);
		panelCategory.add(lblNomCategorie);
		
		JLabel labelCategorie = new JLabel("");
		labelCategorie.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelCategorie.setBounds(20, 46, 190, 38);
		panelCategory.add(labelCategorie);
		
		
		
//==================== PANEL CONTACT ============================================================		
		
		JPanel panelForm = new JPanel();
		panelForm.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Contact", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		panelForm.setBounds(10, 116, 354, 320);
		getContentPane().add(panelForm);
		panelForm.setLayout(null);
		
		JLabel label = new JLabel("NAME");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 48, 56, 36);
		panelForm.add(label);
		
		JLabel label_1 = new JLabel("MAIL");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(10, 122, 87, 36);
		panelForm.add(label_1);
		
		JLabel label_3 = new JLabel("SURNAME");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(10, 86, 87, 36);
		panelForm.add(label_3);
		
		tf_name = new JTextField();
		tf_name.setColumns(10);
		tf_name.setBounds(121, 57, 176, 20);
		panelForm.add(tf_name);
		
		tf_mail = new JTextField();
		tf_mail.setColumns(10);
		tf_mail.setBounds(121, 131, 176, 20);
		panelForm.add(tf_mail);
		
		tf_numTel = new JTextField();
		tf_numTel.setColumns(10);
		tf_numTel.setBounds(121, 173, 113, 20);
		panelForm.add(tf_numTel);
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createContact();
				searchFillTable();
				FunctionDatabase.fillCombo(comboSociety);
			}
		});
		btnCreate.setIcon(new ImageIcon("C:\\Users\\simon_bens\\Pictures\\add-icon (16px).png"));
		btnCreate.setForeground(new Color(34, 139, 34));
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCreate.setBounds(20, 211, 101, 27);
		panelForm.add(btnCreate);
		
		JButton btnSave = new JButton("UPDATE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update
				updateData();
				FunctionDatabase.fillCombo(comboSociety);
			}
		});
		btnSave.setIcon(new ImageIcon("C:\\Users\\simon_bens\\Pictures\\system-software-update-icon (16px.png"));
		btnSave.setForeground(new Color(205, 133, 63));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(131, 211, 103, 27);
		panelForm.add(btnSave);
		
		tf_surname = new JTextField();
		tf_surname.setColumns(10);
		tf_surname.setBounds(121, 95, 176, 20);
		panelForm.add(tf_surname);
		
		JLabel label_4 = new JLabel("TELEPHONE +33");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(10, 164, 101, 36);
		panelForm.add(label_4);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteData();
				FunctionDatabase.fillCombo(comboSociety);
			}
		});
		btnDelete.setIcon(new ImageIcon("C:\\Users\\simon_bens\\Pictures\\Close-icon.png"));
		btnDelete.setForeground(new Color(165, 42, 42));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(246, 211, 98, 27);
		panelForm.add(btnDelete);
		
		JLabel label_5 = new JLabel("SOCIETY");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_5.setBounds(10, 11, 87, 36);
		panelForm.add(label_5);
		
		tf_society = new JTextField();
		tf_society.setColumns(10);
		tf_society.setBounds(121, 20, 176, 20);
		panelForm.add(tf_society);
		
		JButton btnDetailSociety = new JButton(" Detail Society");
		btnDetailSociety.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SocietyFrame sf = new SocietyFrame();
				sf.setVisible(true);
			}
		});
		btnDetailSociety.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDetailSociety.setIcon(new ImageIcon("C:\\Users\\simon_bens\\Pictures\\buisness-icon.png"));
		btnDetailSociety.setBounds(117, 269, 140, 27);
		panelForm.add(btnDetailSociety);
	}
}
