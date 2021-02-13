package br.com.brainweb.interview.model;

import java.util.Optional;
import java.util.UUID;

public class Hero {
    
    private UUID id;
    private String name;
    private String race;
    private PowerStats powerStats;
    
    
    public Hero(String name, String race, PowerStats powerStats) {
	super();
	this.name = name;
	this.race = race;
	this.powerStats = Optional.ofNullable(powerStats).orElse(new PowerStats(0, 0, 0, 0));
    }

    public Hero(UUID id, String name, String race, PowerStats powerStats) {
	super();
	this.id = id;
	this.name = name;
	this.race = race;
	this.powerStats =  Optional.ofNullable(powerStats).orElse(new PowerStats(0, 0, 0, 0));;
    }

    
    public String getIdString() {
	return this.id.toString();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public PowerStats getPowerStats() {
        return powerStats;
    }
    
    public void setPowerStats(PowerStats powerStats) {
        this.powerStats = powerStats;
    }

    @Override
    public String toString() {
	return "Hero [id=" + id + ", name=" + name + ", race=" + race + ", powerStats=" + powerStats + "]";
    }

}
