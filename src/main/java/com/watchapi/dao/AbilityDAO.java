package com.watchapi.dao;

import java.util.List;

import com.watchapi.model.Ability;

public interface AbilityDAO {

	public List<Ability> getAbilityList();
	
	public Ability getAbilityById(int id);
	
	public void addAbilities();

	public List<Ability> getAbilitiesOfHero(int id);
 }
