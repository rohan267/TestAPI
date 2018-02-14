package com.watchapi.TestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.watchapi.dao.HeroDAO;

@RestController
public class WatchAPIController {

	@Autowired
	private HeroDAO heroDAO;
	
	
	@RequestMapping(path="/getList")
	public String getList() {
		
		heroDAO.getHero(1);
		return "hello";
	}
	
	
	@RequestMapping(path="/addHeros")
	public void addHeros() {
		
		heroDAO.addHeros();
//		return "hello";
	}
	
}
