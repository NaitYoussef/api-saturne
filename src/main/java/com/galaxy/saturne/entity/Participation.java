/**
 * 
 */
package com.galaxy.saturne.entity;

import java.util.Date;

/**
 * @author GRIECH
 *
 */
public class Participation {

	private Participant participant;
	private double montant;
	private Date date;

	/**
	 * @param participant
	 * @param montant
	 * @param date
	 */
	public Participation(Participant participant, double montant, Date date) {
		super();
		this.participant = participant;
		this.montant = montant;
		this.date = date;
	}

	/**
	 * 
	 */
	public Participation() {
	}

	/**
	 * @return the participant
	 */
	public Participant getParticipant() {
		return participant;
	}

	/**
	 * @param participant
	 *            the participant to set
	 */
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * @param amount
	 *            the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
