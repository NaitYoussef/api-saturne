/**
 * 
 */
package com.galaxy.saturne.entity;

import java.util.Date;
import java.util.List;

/**
 * @author GRIECH
 *
 */
public class Tour {

	private int ordre;
	private List<Participation> participations;// Participant -> Participation
	private Participant beneficiaire;
	private Date dateRetrait;
	private boolean actif;

	/**
	 * @param id
	 * @param ordre
	 * @param participations
	 * @param beneficiaire
	 * @param dateRetrait
	 * @param actif
	 */
	public Tour(int ordre, List<Participation> participations, Participant beneficiaire,
			Date dateRetrait, boolean actif) {
		super();
		this.setOrdre(ordre);
		this.participations = participations;
		this.beneficiaire = beneficiaire;
		this.dateRetrait = dateRetrait;
		this.actif = actif;
	}

	/**
	 * 
	 */
	public Tour() {
	}

	/**
	 * @return the participations
	 */
	public List<Participation> getParticipations() {
		return participations;
	}

	/**
	 * @param participations
	 *            the participations to set
	 */
	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	/**
	 * @return the beneficiaire
	 */
	public Participant getBeneficiaire() {
		return beneficiaire;
	}

	/**
	 * @param beneficiaire
	 *            the beneficiaire to set
	 */
	public void setBeneficiaire(Participant beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

	/**
	 * @return the dateRetrait
	 */
	public Date getDateRetrait() {
		return dateRetrait;
	}

	/**
	 * @param dateRetrait
	 *            the dateRetrait to set
	 */
	public void setDateRetrait(Date dateRetrait) {
		this.dateRetrait = dateRetrait;
	}

	/**
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}

	/**
	 * @param actif
	 *            the actif to set
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}

	/**
	 * @return the ordre
	 */
	public int getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

}
