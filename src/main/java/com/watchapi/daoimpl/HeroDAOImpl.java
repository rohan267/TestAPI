package com.watchapi.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.watchapi.configuration.JDBCTemplateConfig;
import com.watchapi.dao.HeroDAO;
import com.watchapi.model.Hero;

//@Repository("heroDAO")
@Configuration
@Transactional
//@Profile({"dev", "default"})
public class HeroDAOImpl implements HeroDAO {

	private JdbcTemplate jdbcTemplate;

	/**
	 * Returns list of all heros from database
	 * @return
	 */
	@Override
	public List<Hero> getHerosList() {
		
        jdbcTemplate = JDBCTemplateConfig.getJDBCTemplate();

        String query = "SELECT * FROM HERO";
        
        List<Hero> herosList=null;
        try {
        		herosList  = jdbcTemplate.query(query, new BeanPropertyRowMapper(Hero.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return herosList;
	}

	/**
	 * Returns Hero detail from database using hero id
	 * @param id
	 * @return
	 */
	@Override
	public Hero getHero(int id) {
		
		 jdbcTemplate = JDBCTemplateConfig.getJDBCTemplate();

	        String query = "SELECT * FROM HERO where id=?";
	        
	        Hero hero=null;
	        try {
        				hero = (Hero)jdbcTemplate.queryForObject(query, new Object[] {id}, new BeanPropertyRowMapper(Hero.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return hero;
	}

	@Override
	public boolean addHeros() {
	
		String url = "https://overwatch-api.net/api/v1/hero";
		
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
			List<Map> herosDataList = (ArrayList<Map>)responseEntity.getBody().get("data");

	        jdbcTemplate = JDBCTemplateConfig.getJDBCTemplate();

	        for(Map<String, Object> obj : herosDataList) {
	        	
	        		jdbcTemplate.update(new PreparedStatementCreator() {
	        	 
	            public PreparedStatement createPreparedStatement(Connection con)
	                    throws SQLException {
	                PreparedStatement stmt = con
	                        .prepareStatement("INSERT into Hero values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	                stmt.setInt(1, Integer.valueOf(obj.get("id").toString()));
	                
	                if(obj.get("name")!=null)
	                		stmt.setString(2, obj.get("name").toString());
	                else
	                		stmt.setString(2, obj.get("").toString());
	                
	                if(obj.get("description")!=null)
	                		stmt.setString(3, obj.get("description").toString());
	                else
                			stmt.setString(3, obj.get("").toString());
	
	                if(obj.get("health")!=null)
	                		stmt.setInt(4, Integer.valueOf(obj.get("health").toString()));
	                else
	                		stmt.setInt(4, 0);
	                
	                if(obj.get("armour")!=null)
	                		stmt.setInt(5, Integer.valueOf(obj.get("armour").toString()));
	                else
	                		stmt.setInt(5, 0);

	                
	                if(obj.get("shield")!=null)
	                		stmt.setInt(6, Integer.valueOf(obj.get("shield").toString()));
	                else
	                		stmt.setInt(6, 0);

                	    
	                if(obj.get("real_name")!=null)
	                		stmt.setString(7, obj.get("real_name").toString());
	                else
	                		stmt.setString(7, "");
                	    
	                if(obj.get("age")!=null)
	                		stmt.setInt(8, Integer.valueOf(obj.get("age").toString()));	
	                else
	                		stmt.setInt(8, 0);
                	    
                	    if(obj.get("height")!=null)
                	    		stmt.setFloat(9,Float.valueOf(obj.get("height").toString()));
                	    else
                	    		stmt.setFloat(9,new Float(0.0));
                	    
                	    if(obj.get("affiliation")!=null)
                	    		stmt.setString(10, obj.get("affiliation").toString());
                	    else
                	    		stmt.setString(10, "");
                	    
                	    if(obj.get("base_of_operations")!=null)
                	    		stmt.setString(11,obj.get("base_of_operations").toString());
                	    else
                	    		stmt.setString(11, "");
                	    
                	    if(obj.get("difficulty")!=null)
                	    		stmt.setInt(12, Integer.valueOf(obj.get("difficulty").toString()));
                	    else
                	    		stmt.setInt(12, 0);
                	    
                	    if(obj.get("url")!=null)
                	    		stmt.setString(13, obj.get("url").toString());
                	    else
                	    		stmt.setString(13, "");
	                
	                return stmt;
	            }
	        });
	        
	        }
	        return true;
		} catch (RestClientException e) {
			e.printStackTrace();
			return false;
		} catch(Exception _ex) {
			_ex.printStackTrace();
			return false;
		}	
	}
	
}