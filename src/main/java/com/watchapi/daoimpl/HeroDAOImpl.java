package com.watchapi.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
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

	@Override
	public List<String> getHerosList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hero getHero(int id) {
		
		System.out.println("HeroDAOImpl getHero");
		return null;
	}

	@Override
	public void addHeros() {
	
		String url = "http://overwatch-api.net/api/v1/hero";
		String jsonPlaceHolder = "https://jsonplaceholder.typicode.com/posts";
		
		RestTemplate restTemplate = new RestTemplate();
		try {
//			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("X-TP-DeviceID", "integration1.0.0");

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<Hero[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Hero[].class);

			//Mall[] resp = respEntity.getBody();
			
//			ResponseEntity<Hero[]> responseEntity = restTemplate.getForEntity(url, Hero[].class); // working
			Hero[] objects = responseEntity.getBody();
//			MediaType contentType = responseEntity.getHeaders().getContentType();
//			HttpStatus statusCode = responseEntity.getStatusCode();
			
	        jdbcTemplate = JDBCTemplateConfig.getJDBCTemplate();

	        for(Hero obj : objects) {
	        	
	        jdbcTemplate.update(new PreparedStatementCreator() {
	        	 
	            public PreparedStatement createPreparedStatement(Connection con)
	                    throws SQLException {
	                PreparedStatement stmt = con
	                        .prepareStatement("INSERT into Hero values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	                stmt.setString(2, obj.getName());
	                stmt.setString(3, obj.getDescription());
	                stmt.setInt(4, obj.getHealth());
	                stmt.setInt(5, obj.getArmour());
	                stmt.setInt(5, obj.getShield());
                	    stmt.setString(5, obj.getReal_name());
                	    stmt.setInt(6, obj.getAge());
                	    stmt.setFloat(7,obj.getHeight());
                	    stmt.setString(8, obj.getAffiliation());
                	    stmt.setString(9,obj.getBase_of_operations());
                	    stmt.setInt(10, obj.getDifficulty());
                	    stmt.setString(11, obj.getHeroURL());
	                
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
}