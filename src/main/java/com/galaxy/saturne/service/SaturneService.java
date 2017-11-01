package com.galaxy.saturne.service;

import java.util.Collection;

import com.galaxy.saturne.entity.Saturne;

public interface SaturneService {

	public Collection<Saturne> getAllSaturnes();

	/**
	 * Créer une Saturne
	 * @param saturne
	 */
	public Saturne insertSaturne(Saturne saturne);

	/**
	 * Mettre à jour une Saturne
	 * @param saturne
	 */
	public Saturne updateSaturne(int id, Saturne saturne);

	/**
	 * Démarer une Saturne
	 * @param id
	 * @param utilisateurId
	 */
	public Saturne demarrerSaturne(int id, int utilisateurId);

	/**
	 * Terminer une Saturne
	 * @param id
	 * @param utilisateurId
	 */
	public Saturne terminerSaturne(int id, int utilisateurId);

	/**
	 * Participer à Une Saturne
	 * @param id
	 * @param utilisateurId
	 */
	public Saturne participerSaturne(int id, int utilisateurId);

	/**
	 * 
	 * @param id
	 * @param utilisateurId
	 */
	public Saturne retirerSaturne(int id, int utilisateurId);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Saturne getSaturne(int id);

}