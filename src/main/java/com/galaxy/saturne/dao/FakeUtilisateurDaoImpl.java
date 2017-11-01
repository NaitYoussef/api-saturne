package com.galaxy.saturne.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.galaxy.saturne.entity.Utilisateur;

@Repository
@Qualifier("fakeData")
public class FakeUtilisateurDaoImpl implements UtilisateurDao {

	private static Map<Integer, Utilisateur> utilisateurs;

	static {

		utilisateurs = new HashMap<Integer, Utilisateur>() {

			{
				put(1, new Utilisateur(1, "rojola", "rojola@gmail.com"));
				put(2, new Utilisateur(2, "Alex U", "Finance@gmail.com"));
				put(3, new Utilisateur(3, "Anna", "Maths@gmail.com"));
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.galaxy.saturne.dao.UserDao#getAllUsers()
	 */
	@Override
	public Collection<Utilisateur> getAllUtilisateurs() {
		return this.utilisateurs.values();
	}

	@Override
	public Utilisateur getUtilisateur(int id) {
		return this.utilisateurs.get(id);
	}
	
	@Override
	public Utilisateur insertUtilisateur(Utilisateur utilisateur) {
		utilisateur.setId(this.utilisateurs.size() + 1);
		this.utilisateurs.put(utilisateur.getId(), utilisateur);
		return utilisateur;
	}
}
