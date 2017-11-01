package com.galaxy.saturne.dao;

import java.util.Collection;

import com.galaxy.saturne.entity.Utilisateur;

public interface UtilisateurDao {

	public Collection<Utilisateur> getAllUtilisateurs();
	
	public Utilisateur getUtilisateur(int id);

	public Utilisateur insertUtilisateur(Utilisateur utilisateur);

}