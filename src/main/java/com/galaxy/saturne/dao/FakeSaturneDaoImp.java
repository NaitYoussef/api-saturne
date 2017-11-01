/**
 * 
 */
package com.galaxy.saturne.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.galaxy.saturne.entity.Saturne;

/**
 * @author GRIECH
 *
 */
@Repository
@Qualifier("fakeData")
public class FakeSaturneDaoImp implements SaturneDao {

	private static Map<Integer, Saturne> saturnes;

	static {

		saturnes = new HashMap<Integer, Saturne>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.galaxy.saturne.dao.UserDao#getAllUsers()
	 */
	@Override
	public Collection<Saturne> getAllSaturnes() {
		return this.saturnes.values();
	}

	@Override
	public Saturne insertSaturne(Saturne saturne) {
		saturne.setId(this.saturnes.values().size() + 1);
		this.saturnes.put(saturne.getId(), saturne);
		return saturne;
	}
	
	@Override
	public void updateSaturne(int id, Saturne saturne) {
		this.saturnes.put(id, saturne);
	}
	
	@Override
	public Saturne getSaturne(Integer id) {
		return this.saturnes.get(id);
	}
}
