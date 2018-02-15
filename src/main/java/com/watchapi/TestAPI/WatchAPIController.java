package com.watchapi.TestAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.watchapi.dao.AbilityDAO;
import com.watchapi.dao.HeroDAO;
import com.watchapi.model.Ability;
import com.watchapi.model.Hero;

@RestController
public class WatchAPIController {

	@Autowired
	private HeroDAO heroDAO;
	
	@Autowired
	private AbilityDAO abilityDAO;
	
	@RequestMapping(path="/welcome")
	public String welcome() {
		
		return "Hello";
	}
	
	/**
	 * Get list of heros
	 * @return
	 */
	@RequestMapping(path="/getList")
	public List<Hero> getList() {
		
		List<Hero> heros = heroDAO.getHerosList();
		return heros;
	}
	
	/**
	 * Get Hero By Id
	 * @param id
	 * @return
	 */
	
	@RequestMapping(path="/getHeroById/{id}")
	@ResponseBody
	public Hero getHeroById(@PathVariable("id") int id) {
		
		Hero hero = heroDAO.getHero(id);
		return hero;
	}
	
	/**
	 * Get abilities of Hero by id
	 * @param id
	 * @return
	 */
	@RequestMapping(path="/getHeroAbilityList/{id}")
	@ResponseBody
	public List<Ability> getHeroByAbility(@PathVariable("id") int id) {
		
		List<Ability> abilitiesOfHero = abilityDAO.getAbilitiesOfHero(id);
		return abilitiesOfHero;
	}
	
	/**
	 * Get ability List
	 * @return
	 */
	@RequestMapping(path="/getAbilities")
	public List<Ability> getAbilityList() {
		
		List<Ability> abilities = abilityDAO.getAbilityList();
		return abilities;
	}
	
	/**
	 * Get Ability by id
	 * @param id
	 * @return
	 */
	@RequestMapping(path="/getAbilityById/{id}")
	public Ability getAbilityById(@PathVariable("id") int id) {
		
		Ability ability = abilityDAO.getAbilityById(id);
		return ability;
	}
	
	/**
	 * Add abilities
	 */
	@RequestMapping(path="/addAbilities")
	public void addAbilityList() {
		
		abilityDAO.addAbilities();
	}
	
	/**
	 * add heros
	 */
	@RequestMapping(path="/addHeros")
	public void addHeros() {
		
		heroDAO.addHeros();
//		return "hello";
	}
	
}
