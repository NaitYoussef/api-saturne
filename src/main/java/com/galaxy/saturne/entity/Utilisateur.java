/**
 * 
 */
package com.galaxy.saturne.entity;

/**
 * @author GRIECH
 *
 */
public class Utilisateur {

	private int id;
	private String name;
	private String mail;

	/**
	 * @param id
	 * @param name
	 * @param mail
	 */
	public Utilisateur(int id, String name, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	/**
	 * 
	 */
	public Utilisateur() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

}
