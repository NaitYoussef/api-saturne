package com.galaxy.saturne.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.galaxy.saturne.dao.UtilisateurDao;
import com.galaxy.saturne.entity.Utilisateur;

@Service
public class UserService {

	@Autowired
	@Qualifier("fakeData")
	private UtilisateurDao userDao;

	public Collection<Utilisateur> getAllUtilisateurs() {
		return this.userDao.getAllUtilisateurs();
	}
	
	public Utilisateur getUtilisateur(int id) {
		return this.userDao.getUtilisateur(id);
	}

	public Utilisateur insertUtilisateur(Utilisateur utilisateur) {
		return this.userDao.insertUtilisateur(utilisateur);
	}
}
