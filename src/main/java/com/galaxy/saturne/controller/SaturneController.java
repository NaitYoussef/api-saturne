package com.galaxy.saturne.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.saturne.entity.Saturne;
import com.galaxy.saturne.service.SaturneService;

@RestController
@RequestMapping("/saturnes")
@CrossOrigin
public class SaturneController {

	@Autowired
    private SaturneService saturneService;
	
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Saturne> getAllSaturnes(){
        return saturneService.getAllSaturnes();
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Saturne insertSaturne(@RequestBody Saturne saturne){
    	return saturneService.insertSaturne(saturne);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Saturne getSaturne(@PathVariable("id") int id){
        return saturneService.getSaturne(id);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Saturne updateSaturne(@PathVariable("id") Integer id,@RequestBody Saturne saturne){
    	return saturneService.updateSaturne(id, saturne);
    }
    
    @RequestMapping(value = "/{id}/debut", method = RequestMethod.PUT)
    public Saturne demarrerSaturne(@PathVariable("id") Integer id, @RequestParam("utilisateurId") int utilisateurId){
    	return saturneService.demarrerSaturne(id, utilisateurId);
    }
    
    @RequestMapping(value = "/{id}/fin", method = RequestMethod.PUT)
    public Saturne terminerSaturne(@PathVariable("id") Integer id, @RequestParam("utilisateurId") int utilisateurId){
    	return saturneService.terminerSaturne(id, utilisateurId);
    }
    
    @RequestMapping(value = "/{id}/participer", method = RequestMethod.PUT)
    public Saturne participerSaturne(@PathVariable("id") Integer id, @RequestParam("utilisateurId") int utilisateurId){
    	return saturneService.participerSaturne(id, utilisateurId);
    }
    
    @RequestMapping(value = "/{id}/retrait", method = RequestMethod.PUT)
    public Saturne retirerSaturne(@PathVariable("id") Integer id, @RequestParam("utilisateurId") int utilisateurId){
    	return saturneService.retirerSaturne(id, utilisateurId);
    }
}
