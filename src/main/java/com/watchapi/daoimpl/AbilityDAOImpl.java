package com.watchapi.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.watchapi.configuration.JDBCTemplateConfig;
import com.watchapi.dao.AbilityDAO;
import com.watchapi.model.Ability;

@Configuration
@Transactional
public class AbilityDAOImpl implements AbilityDAO {

	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Ability> getAbilityList() {
		
		jdbcTemplate = JDBCTemplateConfig.getJDBCTemplate();

        String query = "SELECT * FROM Ability";
        
        List<Ability> abilityList=null;
        try {
        		abilityList  = jdbcTemplate.query(query, new BeanPropertyRowMapper(Ability.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return abilityList;
	}

	@Override
	public Ability getAbilityById(int id) {
		 jdbcTemplate = JDBCTemplateConfig.getJDBCTemplate();

	        String query = "SELECT * FROM Ability where id=?";
	        
	        Ability ability=null;
	        try {
     			ability = (Ability)jdbcTemplate.queryForObject(query, new Object[] {id}, new BeanPropertyRowMapper(Ability.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return ability;
	}

	@Override
	public void addAbilities() {
		String url = "https://overwatch-api.net/api/v1/ability/";
		
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");


			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			@SuppressWarnings("rawtypes")
			ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

			@SuppressWarnings("unchecked")
			List<Map> abilitiesList = (ArrayList<Map>)responseEntity.getBody().get("data");

	        jdbcTemplate = JDBCTemplateConfig.getJDBCTemplate();

	        for(Map<String, Object> obj : abilitiesList) {
	        	
	        		jdbcTemplate.update(new PreparedStatementCreator() {
	        	 
	            public PreparedStatement createPreparedStatement(Connection con)
	                    throws SQLException {
	                PreparedStatement stmt = con
	                        .prepareStatement("INSERT into Ability values (?, ?, ?, ?, ?, ?)");
	                stmt.setInt(1, Integer.valueOf(obj.get("id").toString()));
	                
	                if(obj.get("name")!=null)
	                		stmt.setString(2, obj.get("name").toString());
	                else
	                		stmt.setString(2, obj.get("").toString());
	                
	                if(obj.get("description")!=null)
	                		stmt.setString(3, obj.get("description").toString());
	                else
                			stmt.setString(3, obj.get("").toString());
	
	                if(obj.get("is_ultimate")!=null)
	                		stmt.setBoolean(4, new Boolean(obj.get("is_ultimate").toString()));
	                else
	                		stmt.setBoolean(4, false);
	                
	                if(obj.get("url")!=null)
	                		stmt.setString(5, obj.get("url").toString());
	                else
	                		stmt.setString(5, "");

	                Map<String, Object> hero;
                	    if(obj.get("hero")!=null) {
                	    		hero = (Map<String, Object>)obj.get("hero");
                	    		stmt.setInt(6, Integer.valueOf(hero.get("id").toString()));
                	    } else
                	    		stmt.setInt(6, 0);
	                
	                return stmt;
	            }
	        });
	        
	        }
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch(Exception _ex) {
			_ex.printStackTrace();
		}
		
	}

	@Override
	public List<Ability> getAbilitiesOfHero(int id) {
		
		jdbcTemplate = JDBCTemplateConfig.getJDBCTemplate();

        String query = "SELECT * FROM Ability where heroid=?";
        
        List<Ability> abilityList=null;
        try {
        		abilityList  = jdbcTemplate.query(query,new Object[] {id}, new BeanPropertyRowMapper(Ability.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return abilityList;
	}
	
	

}
