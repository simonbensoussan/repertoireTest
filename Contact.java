package Projets;
/*
 * CLASS STICK WITH CONTACTREPERSITORY CLASS.
 */
public class Contact {
	
	private String society;
	private String name;
	private String surName;
	private String mail;
	private int telephone;
	
	/**
	 * Constructor
	 * @param name
	 * @param mail
	 */
	public Contact(String society,String name, String surName,String mail,int telephone) {
		
		this.society = society;
		this.name = name;
		this.surName = surName;
		this.mail = mail;
		this.telephone = telephone;
	}
	/**
	 * getter/setter
	 * @return
	 */
	
	
	public String getSociety() {
		return society;
	}
	public void setSociety(String society) {
		this.society = society;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return String
				.format("Contact [society=%s, name=%s, surName=%s, mail=%s, telephone=%s]\n",
						society, name, surName, mail, telephone);
	}

}
