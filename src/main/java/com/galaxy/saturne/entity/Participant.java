package com.galaxy.saturne.entity;

public class Participant extends Utilisateur {

	private int ordre;
	private int occurence;

	/**
	 * @param id
	 * @param name
	 * @param mail
	 * @param ordre
	 * @param occurence
	 */
	public Participant(int id, String name, String mail, int ordre, int occurence) {
		super(id, name, mail);
		this.ordre = ordre;
		this.occurence = occurence;
	}

	/**
	 * @param id
	 * @param name
	 * @param mail
	 */
	public Participant() {
		super();
	}

	/**
	 * @return the ordre
	 */
	public int getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre
	 *            the ordre to set
	 */
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	/**
	 * @return the occurence
	 */
	public int getOccurence() {
		return occurence;
	}

	/**
	 * @param occurence
	 *            the occurence to set
	 */
	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}

}
