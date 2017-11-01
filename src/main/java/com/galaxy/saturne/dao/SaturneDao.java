package com.galaxy.saturne.dao;

import java.util.Collection;

import com.galaxy.saturne.entity.Saturne;

public interface SaturneDao {

	public Collection<Saturne> getAllSaturnes();
	
	public Saturne getSaturne(Integer id);

	public Saturne insertSaturne(Saturne saturne);
	
	public void updateSaturne(int id, Saturne saturne);

}