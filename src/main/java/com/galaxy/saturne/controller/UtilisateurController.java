package com.galaxy.saturne.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.saturne.entity.Utilisateur;
import com.galaxy.saturne.service.UserService;

@RestController
@RequestMapping("/utilisateurs")
@CrossOrigin
public class UtilisateurController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Utilisateur> getAllUtilisateurs() {
		return userService.getAllUtilisateurs();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Utilisateur getUtilisateur(@PathVariable("id") int id) {
		return userService.getUtilisateur(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Utilisateur insertUtilisateur(@RequestBody Utilisateur utilisateur) {
		return userService.insertUtilisateur(utilisateur);
	}
}
