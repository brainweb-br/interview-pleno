package br.com.brainweb.interview.core.features.hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@Repository
public class HeroRepository {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HeroRepository.class);
    
    @Autowired
    private DataSource dataSource;
    
    
    public Hero findByName(String name){
	Hero hero = null;
	String query = "select * from hero where name= '" + name+ "' limit 1";
	
	try {
	    Connection con = dataSource.getConnection();
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();
	    if(rs.next()){
		String id = rs.getString("id");
		hero = new Hero(UUID.fromString(id), rs.getString("name"), rs.getString("race"), null);
		
		String powerStatsId = rs.getString("power_stats_id");
		PowerStats powerStats = this.getPowerStatsFor(powerStatsId);
		LOGGER.info("PowerStats::::{}", powerStats );
		hero.setPowerStats(powerStats);
	    }
	    
	    return hero;
	} catch (SQLException e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    e.printStackTrace();
	    return null;
	}
    }
    
    public void create(Hero hero) {
	this.createPowerStatsFor(hero);
	
	Connection con;
	try {
	    con = dataSource.getConnection();
	    PreparedStatement st = con.prepareStatement("insert into hero (name, race, power_stats_id) values (?, ?, ?)");
	    st.setString(1, hero.getName());
	    st.setString(2,  hero.getRace());
	    st.setObject(3, hero.getPowerStats().getId());
	    st.executeUpdate();
	    st.close();
	} catch (SQLException e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    e.printStackTrace();
	}
    }
    
    private void createPowerStatsFor(Hero hero) {
	
   	Connection con;
   	try {
   	    con = dataSource.getConnection();
   	    PreparedStatement st = con.prepareStatement("insert into power_stats (strength, agility, dexterity, intelligence) values (?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
   	    st.setInt(1, hero.getPowerStats().getStrength());
   	    st.setInt(2, hero.getPowerStats().getAgility());
   	    st.setInt(3, hero.getPowerStats().getDexterity());
   	    st.setInt(4, hero.getPowerStats().getIntelligence());
   	    st.executeUpdate();
   	    
   	    try (ResultSet generatedKeys = st.getGeneratedKeys()) {
             if (generatedKeys.next()) {
                 hero.getPowerStats().setId(UUID.fromString(generatedKeys.getString(1)));
             }
             else {
                 throw new SQLException("Creating powerStats failed, no ID obtained.");
             }
         }
   	    st.close();
   	} catch (SQLException e) {
   	    LOGGER.error("{}", e.getMessage(), e);
   	    e.printStackTrace();
   	}
       }
    
    private PowerStats getPowerStatsFor(String id){
	LOGGER.info("Invoking getPowerStatsFor(id), values({})", StringUtils.join(new Object[] { id }, ", "));
	PowerStats powerStats = null;
   	String query = "select * from power_stats where id= '" + id+ "' limit 1";
   	
   	try {
   	    Connection con = dataSource.getConnection();
   	    PreparedStatement st = con.prepareStatement(query);
   	    ResultSet rs = st.executeQuery();
   	    if(rs.next()){		
   		int strength = rs.getInt("strength");
   		int agility = rs.getInt("agility");
   		int dexterity = rs.getInt("dexterity");
   		int intelligence = rs.getInt("intelligence");
   		powerStats = new PowerStats(UUID.fromString(rs.getString("id")),strength, agility, dexterity, intelligence);
   	    }
   	    
   	} catch (SQLException e) {
   	    LOGGER.error("{}", e.getMessage(), e);
   	    e.printStackTrace();
   	}
   	return powerStats;
    }
}
