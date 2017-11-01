package com.galaxy.saturne.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.galaxy.saturne.dao.SaturneDao;
import com.galaxy.saturne.dao.UtilisateurDao;
import com.galaxy.saturne.entity.Participant;
import com.galaxy.saturne.entity.Participation;
import com.galaxy.saturne.entity.Saturne;
import com.galaxy.saturne.entity.Tour;
import com.galaxy.saturne.entity.Utilisateur;

@Service
public class SaturneServiceImpl implements SaturneService {

	@Autowired
	@Qualifier("fakeData")
    private SaturneDao saturneDao;
	
	@Autowired
	@Qualifier("fakeData")
    private UtilisateurDao utilisateurDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.galaxy.saturne.service.SaturneService#getAllSaturnes()
	 */
	@Override
	public Collection<Saturne> getAllSaturnes(){
        return this.saturneDao.getAllSaturnes();
    }

	/* (non-Javadoc)
	 * @see com.galaxy.saturne.service.SaturneService#insertSaturne(com.galaxy.saturne.entity.Saturne)
	 */
	@Override
	public Saturne insertSaturne(Saturne saturne) {
		Saturne newSaturne = null;
		// Au moins un Admin pour créer une nouvelle saturne
		if (saturne.getAdmin() != null) {
			Utilisateur admin = this.utilisateurDao.getUtilisateur(saturne.getAdmin().getId());
			
			if (admin != null 
					&& (saturne.getMensualite()== null || saturne.getMensualite()!= null && saturne.getMensualite() > 0)) {
				saturne.setAdmin(admin);
				// Participants
				if (CollectionUtils.isEmpty(saturne.getParticipants())) {
					saturne.setParticipants(new ArrayList<Participant>());
				}
				// TODO Vérifier l'ordre et l'occurence
				newSaturne = this.saturneDao.insertSaturne(saturne);
			}
		}
		return newSaturne;
	}
	
	/* (non-Javadoc)
	 * @see com.galaxy.saturne.service.SaturneService#updateSaturne(int, com.galaxy.saturne.entity.Saturne)
	 */
	@Override
	public Saturne updateSaturne(int id, Saturne saturne) {
		
		Saturne saturneToUpdate = this.saturneDao.getSaturne(id);
			
		if (saturneToUpdate != null) {
			// Mensualité
			if (saturne.getMensualite() != null && saturne.getMensualite() > 0) {
				saturneToUpdate.setMensualite(saturne.getMensualite());
			}
			// Participants
			Utilisateur participant = null;
			Integer ordre;
			if (!CollectionUtils.isEmpty(saturne.getParticipants())) {
				for (Participant nouveauParticipant : saturne.getParticipants()) {
					if (!participantExistant(nouveauParticipant.getId(), saturneToUpdate)) {
						participant = this.utilisateurDao.getUtilisateur(nouveauParticipant.getId());
						if (participant != null) {
							ordre = getOrdreFromSaturne(saturneToUpdate);
							// TODO Ordre + Occurence
							nouveauParticipant.setOrdre(ordre);
							nouveauParticipant.setOccurence(1);
							nouveauParticipant.setMail(participant.getMail());
							nouveauParticipant.setName(participant.getName());
							saturneToUpdate.getParticipants().add(nouveauParticipant);
						}
					}
				}
			}
			this.saturneDao.updateSaturne(id, saturneToUpdate);
		}
		
		return this.saturneDao.getSaturne(id);
	}
	
	/**
	 * 
	 * @param participantId
	 * @param saturne
	 * @return
	 */
	private boolean participantExistant(Integer participantId, Saturne saturne) {
		boolean existe = false;
		for (Participant particpant: saturne.getParticipants()) {
			if (particpant.getId() == participantId) {
				existe = true;
				break;
			}
		}
		return existe;
	}
	
	/**
	 * 
	 * @param participantId
	 * @param saturne
	 * @return
	 */
	private Participant getParticipantParId(Integer participantId, Saturne saturne) {
		Participant participantParId = null;
		for (Participant particpant: saturne.getParticipants()) {
			if (particpant.getId() == participantId) {
				participantParId = particpant;
				break;
			}
		}
		return participantParId;
	}
	
	/**
	 * 
	 * @param participantId
	 * @param saturne
	 * @return
	 */
	private Participant getParticipantParOrdre(Integer ordre, Saturne saturne) {
		Participant participantParId = null;
		for (Participant particpant: saturne.getParticipants()) {
			if (particpant.getOrdre() == ordre) {
				participantParId = particpant;
				break;
			}
		}
		return participantParId;
	}

	/**
	 * @param saturneToUpdate
	 * @return
	 */
	private int getOrdreFromSaturne(Saturne saturneToUpdate) {
		return saturneToUpdate.getParticipants().size() + 1;
	}
	
	/* (non-Javadoc)
	 * @see com.galaxy.saturne.service.SaturneService#demarrerSaturne(int, int)
	 */
    @Override
	public Saturne demarrerSaturne(int id, int utilisateurId) {
    	
    	Saturne saturne  = this.saturneDao.getSaturne(id);
    	
    	if (peutDemarrer(utilisateurId, saturne)) {
    		
	    		saturne.setToursHistoriques(new ArrayList<Tour>());
	    		Tour premierTour = initialiserNouveauTour(saturne);
	    		saturne.setTourActif(premierTour);
	    		saturne.setActive(true);
	    		this.saturneDao.updateSaturne(id, saturne);
    	}
    	
    	return this.saturneDao.getSaturne(id);
    }

	/**
	 * @param utilisateurId
	 * @param saturne
	 * @return
	 */
	private boolean peutDemarrer(int utilisateurId, Saturne saturne) {
		return !saturne.isActive() 
    			&& saturne.getAdmin() != null
    			&& saturne.getAdmin().getId() == utilisateurId
    			&& saturne.getMensualite() != null
    			&& saturne.getMensualite() > 0
    			&& !CollectionUtils.isEmpty(saturne.getParticipants());
	}

	/**
	 * @param beneficiaire
	 * @return
	 */
	private Tour initialiserNouveauTour(Saturne saturne) {
		int nombreParticipation = calculerNombreParticipations(saturne);
		int beneficiaireOrdre = (saturne.getToursHistoriques().size()%nombreParticipation)+1;
		Participant beneficiaire = getParticipantParOrdre(beneficiaireOrdre, saturne);
		int tourOrdre = saturne.getToursHistoriques().size() + 1;
		Tour premierTour = new Tour(tourOrdre,
									new ArrayList<Participation>(),
									beneficiaire,
									null,
									true);
		return premierTour;
	}

    /**
     * Calculer le nombre de participation requise pour retirer une Saturne
     * @param saturne
     * @return
     */
	private int calculerNombreParticipationsRecquise(Saturne saturne, int beneficiaireId) {
		int nombreParticipation = 0;
		for (Participant participant : saturne.getParticipants()) {
			if (beneficiaireId != participant.getId()) {
				nombreParticipation += participant.getOccurence();
			}
		}
		return nombreParticipation;
	}
	
	/**
     * Calculer le nombre de participation global
     * @param saturne
     * @return
     */
	private int calculerNombreParticipations(Saturne saturne) {
		int nombreParticipation = 0;
		for (Participant participant : saturne.getParticipants()) {
			nombreParticipation += participant.getOccurence();
		}
		return nombreParticipation;
	}
    
	/* (non-Javadoc)
	 * @see com.galaxy.saturne.service.SaturneService#terminerSaturne(int, int)
	 */
    @Override
	public Saturne terminerSaturne(int id, int utilisateurId){
    	
    	Saturne saturne  = this.saturneDao.getSaturne(id);
    	
    	if (peutTerminer(utilisateurId, saturne)) {
	    	
	    	saturne.setActive(false);
	    	this.saturneDao.updateSaturne(id, saturne);
    	}
    	
    	return this.saturneDao.getSaturne(id);
    }

	/**
	 * @param utilisateurId
	 * @param saturne
	 * @return
	 */
	private boolean peutTerminer(int utilisateurId, Saturne saturne) {
		return saturne.isActive() 
    			&& saturne.getAdmin() != null
    			&& saturne.getAdmin().getId() == utilisateurId;
	}
    
    /* (non-Javadoc)
	 * @see com.galaxy.saturne.service.SaturneService#participerSaturne(int, int)
	 */
    @Override
	public Saturne participerSaturne(int id, int utilisateurId){
    	Saturne saturne = saturneDao.getSaturne(id);
    	
    	// Saturne Active
    	if (saturne != null && saturne.isActive()) {
    		// Participant peut participer ?
    		Participant participant = getParticipantParId(utilisateurId, saturne);
    		boolean peutParticiper = peutParticiperSaturne(participant, saturne);
    		if (peutParticiper) {
    			Participation participation = new Participation(participant, saturne.getMensualite(), new Date());
    			saturne.getTourActif().getParticipations().add(participation);
    			saturneDao.updateSaturne(id, saturne);
    		}
    	}
    	
    	return this.saturneDao.getSaturne(id);
    }

	/**
	 * @param utilisateurId
	 * @param saturne
	 * @param tourActif
	 * @param occurence
	 * @return
	 */
	private boolean peutParticiperSaturne(Participant participant, Saturne saturne) {
		boolean peutParticiper = false;
		Tour tourActif = saturne.getTourActif();
		if (participant != null) {
			int occurence = participant.getOccurence();
			peutParticiper = peutParticiperTour(participant.getId(), occurence, tourActif);
		}
		
		return peutParticiper;
	}
	
	private boolean peutParticiperTour(int utilisateurId, int occurence, Tour tour) {
		boolean peutParticiper = false;
		if (tour.getBeneficiaire().getId() != utilisateurId) {
			int occurenceTour = 0;
			for (Participation participation : tour.getParticipations()) {
				if (participation.getParticipant().getId() == utilisateurId) {
					occurenceTour++;
				}
			}
			peutParticiper = occurenceTour < occurence;
		}
		return peutParticiper;
	}
	
	/**
	 * @param utilisateurId
	 * @param saturne
	 * @param tourActif
	 * @param occurence
	 * @return
	 */
	private boolean peutRetirer(Participant participant, Saturne saturne) {
		Tour tourActif = saturne.getTourActif();
		int beneficiaireId = tourActif.getBeneficiaire().getId();
		boolean peutRetirer = beneficiaireId == participant.getId();
		if (peutRetirer) {
			int nombreParticipation = tourActif.getParticipations().size();
			int nombrePaticipationsRequise = calculerNombreParticipationsRecquise(saturne, beneficiaireId);
			peutRetirer = nombreParticipation == nombrePaticipationsRequise;
		}
		return peutRetirer;
	}
    
    /* (non-Javadoc)
	 * @see com.galaxy.saturne.service.SaturneService#retirerSaturne(int, int)
	 */
    @Override
	public Saturne retirerSaturne(int id, int utilisateurId){
    	
    	Saturne saturne = saturneDao.getSaturne(id);
    	
    	// Saturne Active
    	if (saturne.isActive()) {
    		// Participant peut retirer ?
    		Participant participant = getParticipantParId(utilisateurId, saturne);
    		boolean peutRetirer = peutRetirer(participant, saturne);
    		if (peutRetirer) {
    			// Retrait
    			Tour tourActif = saturne.getTourActif();
    			tourActif.setDateRetrait(new Date());
    			tourActif.setActif(false);
    			saturne.getToursHistoriques().add(tourActif);
    			
    			// Initialiser un nouveau tour
    			Tour nouveauTour = initialiserNouveauTour(saturne);
    			saturne.setTourActif(nouveauTour);
    			
    			saturneDao.updateSaturne(id, saturne);
    		}
    	}
    	
    	return this.saturneDao.getSaturne(id);
    }

    /* (non-Javadoc)
	 * @see com.galaxy.saturne.service.SaturneService#getSaturne(int)
	 */
	@Override
	public Saturne getSaturne(int id) {
		return this.saturneDao.getSaturne(id);
	}
}
