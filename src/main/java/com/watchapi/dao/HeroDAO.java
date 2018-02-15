package com.watchapi.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.watchapi.model.Hero;

/**
 * 
 * 
 https://overwatch-api.net/api/v1/hero/
https://overwatch-api.net/api/v1/hero/{hero_id}
https://overwatch-api.net/api/v1/ability/
https://overwatch-api.net/api/v1/ability/{ability_id}


/api/heros - hero list
/api/heros/{hero_id} - hero data
/api/heros/{hero_id}/abilities - hero ability list
/api/abilities/ - ability list
/api/abilities/{ability_id} - ability data

 * @author rohanparekh
 *
 */
public interface HeroDAO {

	public List<Hero> getHerosList();
	
	public Hero getHero(int id);
	
	public boolean addHeros();
}
