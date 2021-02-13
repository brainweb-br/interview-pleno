package br.com.brainweb.interview.core.features.hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.sql.DataSource;

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
	String query = "select * from hero inner join power_stats on hero.power_stats_id = power_stats.id where name= '" + name+ "' limit 1";
	
	try {
	    Connection con = dataSource.getConnection();
	    PreparedStatement st = con.prepareStatement(query);
	    ResultSet rs = st.executeQuery();
	    if(rs.next()){
		PowerStats powerStats = new PowerStats(UUID.fromString(rs.getString("power_stats_id")), rs.getInt("strength"), rs.getInt("agility"), rs.getInt("dexterity"), rs.getInt("intelligence"));
		String id = rs.getString("id");
		hero = new Hero(UUID.fromString(id), rs.getString("name"), rs.getString("race"), powerStats);
	    }
	    st.close();
	    con.close();
	    return hero;
	} catch (SQLException e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    e.printStackTrace();
	    return null;
	}
    }
    
    public Hero findById(String id){
	Hero hero = null;
	String query = "select * from hero inner join power_stats on hero.power_stats_id = power_stats.id where hero.id= ? limit 1";
	
	try {
	    Connection con = dataSource.getConnection();
	    PreparedStatement st = con.prepareStatement(query);
	    st.setObject(1, UUID.fromString(id));
	    ResultSet rs = st.executeQuery();
	    if(rs.next()){
		PowerStats powerStats = new PowerStats(UUID.fromString(rs.getString("power_stats_id")), rs.getInt("strength"), rs.getInt("agility"), rs.getInt("dexterity"), rs.getInt("intelligence"));
		hero = new Hero(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("race"), powerStats);
	    }
	    st.close();
	    con.close();
	    return hero;
	} catch (SQLException e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    e.printStackTrace();
	    return null;
	}
    }
    
    public void create(Hero hero) {
	try {
	    Connection con = dataSource.getConnection();
	    this.createPowerStatsFor(hero, con);
	    PreparedStatement st = con.prepareStatement("insert into hero (name, race, power_stats_id) values (?, ?, ?)");
	    st.setString(1, hero.getName());
	    st.setString(2,  hero.getRace());
	    st.setObject(3, hero.getPowerStats().getId());
	    st.executeUpdate();
	    st.close();
	    con.close();
	} catch (SQLException e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    e.printStackTrace();
	}
    }
    
    public void update(Hero hero) {
	try {
	    Connection con = dataSource.getConnection();
	    this.updatePowerStatsFor(hero, con);
	    PreparedStatement st = con.prepareStatement("update hero set name=?, race=? where id=?");
	    st.setString(1, hero.getName());
	    st.setString(2,  hero.getRace());
	    st.setObject(3,  hero.getId());
	    st.executeUpdate();
	    st.close();
	    con.close();
	} catch (SQLException e) {
	    LOGGER.error("{}", e.getMessage(), e);
	    e.printStackTrace();
	}
    }
    
    public void delete(String id) {
	try {
	    Connection con = dataSource.getConnection();
	    Hero hero = this.findById(id, con);
	    if (hero == null) {
		LOGGER.warn("Hero not found.");
		return;
	    }
	    PreparedStatement st = con.prepareStatement("delete from hero where id=?");
	    st.setObject(1, UUID.fromString(id));
	    st.executeUpdate();
	    st.close();

	    this.deletePowerStatsFor(hero, con);
	    con.close();
	} catch (Exception e) {
	    LOGGER.error("{}", e.getMessage(), e);
	}
    }
    
    private void deletePowerStatsFor(Hero hero, Connection con) {
	try {
	    con = dataSource.getConnection();
	    PreparedStatement st = con.prepareStatement("delete from power_stats where id=?");
	    st.setObject(1, hero.getPowerStats().getId());
	    st.executeUpdate();
	    st.close();
	} catch (Exception e) {
	    LOGGER.error("{}", e.getMessage(), e);
	}
    }
    private void createPowerStatsFor(Hero hero, Connection con) {
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
    
   
    private Hero findById(String id, Connection con){
   	Hero hero = null;
   	String query = "select * from hero inner join power_stats on hero.power_stats_id = power_stats.id where hero.id= ? limit 1";
   	
   	try {
   	    PreparedStatement st = con.prepareStatement(query);
   	    st.setObject(1, UUID.fromString(id));
   	    ResultSet rs = st.executeQuery();
   	    if(rs.next()){
   		PowerStats powerStats = new PowerStats(UUID.fromString(rs.getString("power_stats_id")), rs.getInt("strength"), rs.getInt("agility"), rs.getInt("dexterity"), rs.getInt("intelligence"));
   		hero = new Hero(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("race"), powerStats);
   	    }
   	    st.close();
   	    return hero;
   	} catch (SQLException e) {
   	    LOGGER.error("{}", e.getMessage(), e);
   	    e.printStackTrace();
   	    return null;
   	}
       }
       
    
 private void updatePowerStatsFor(Hero hero, Connection con) {
   	try {
   	    con = dataSource.getConnection();
   	    PreparedStatement st = con.prepareStatement("update power_stats set strength=?, agility=?, dexterity=?, intelligence=? where id=?");
   	    st.setInt(1, hero.getPowerStats().getStrength());
   	    st.setInt(2, hero.getPowerStats().getAgility());
   	    st.setInt(3, hero.getPowerStats().getDexterity());
   	    st.setInt(4, hero.getPowerStats().getIntelligence());
   	    st.setObject(5, hero.getPowerStats().getId());
   	    st.executeUpdate();
   	    st.close();
   	} catch (SQLException e) {
   	    LOGGER.error("{}", e.getMessage(), e);
   	    e.printStackTrace();
   	}
       }
}
