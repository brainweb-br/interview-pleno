package br.com.brainweb.interview.core.features.hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.model.Hero;

@Repository
public class HeroRepository {
    
    @Autowired
    private DataSource dataSource;
    
    
    public List<Hero> getAll() throws SQLException{
	List<Hero> heroes =  new ArrayList<Hero>();
	String query = "select * from hero";
	
	try {
	    Connection con = dataSource.getConnection();
	    PreparedStatement pst = con.prepareStatement(query);
	    ResultSet rs = pst.executeQuery();
	    
	    while(rs.next()) {
		Hero hero = new Hero();
		    
		    
		heroes.add(hero);
	    }
	    
	} finally {
	    
	}
	    
	

	return heroes;
    }
    
    public Hero createHero(Hero hero) {
	
	
	return null;
    }
}
