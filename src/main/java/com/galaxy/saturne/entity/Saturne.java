package com.galaxy.saturne.entity;

import java.util.List;

public class Saturne {

	private int id;
	private List<Tour> toursHistoriques;
	private Tour tourActif;
	private Utilisateur admin;
	private List<Participant> participants;// Clé Utilisateur Id => Valeur Utilisateur
	private Double mensualite;
	private boolean active = false;

	/**
	 * @param id
	 * @param toursHistoriques
	 * @param tourActif
	 * @param admin
	 * @param participantsParOrdre
	 * @param participants
	 * @param occurencesParUtilisateur
	 * @param mensualite
	 * @param active
	 */
	public Saturne(int id, List<Tour> toursHistoriques, Tour tourActif, Utilisateur admin,
			List<Participant> participants, Double mensualite, boolean active) {
		super();
		this.id = id;
		this.toursHistoriques = toursHistoriques;
		this.tourActif = tourActif;
		this.admin = admin;
		this.participants = participants;
		this.mensualite = mensualite;
		this.active = active;
	}

	/**
	 * 
	 */
	public Saturne() {
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
	 * @return the toursHistoriques
	 */
	public List<Tour> getToursHistoriques() {
		return toursHistoriques;
	}

	/**
	 * @param toursHistoriques
	 *            the toursHistoriques to set
	 */
	public void setToursHistoriques(List<Tour> toursHistoriques) {
		this.toursHistoriques = toursHistoriques;
	}

	/**
	 * @return the tourActif
	 */
	public Tour getTourActif() {
		return tourActif;
	}

	/**
	 * @param tourActif
	 *            the tourActif to set
	 */
	public void setTourActif(Tour tourActif) {
		this.tourActif = tourActif;
	}

	/**
	 * @return the participants
	 */
	public List<Participant> getParticipants() {
		return participants;
	}

	/**
	 * @param participants
	 *            the participants to set
	 */
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	/**
	 * @return the mensualite
	 */
	public Double getMensualite() {
		return mensualite;
	}

	/**
	 * @param mensualite
	 *            the mensualite to set
	 */
	public void setMensualite(Double mensualite) {
		this.mensualite = mensualite;
	}

	/**
	 * @return the admin
	 */
	public Utilisateur getAdmin() {
		return admin;
	}

	/**
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(Utilisateur admin) {
		this.admin = admin;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

}
