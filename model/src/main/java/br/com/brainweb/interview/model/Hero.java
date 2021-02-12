package br.com.brainweb.interview.model;

import java.util.UUID;

public class Hero {
    
    private UUID id;
    private String name;
    private String race;
    private UUID powerStatsId;
    
    
    public Hero(String name, String race, UUID powerStatsId) {
	super();
	this.name = name;
	this.race = race;
	this.powerStatsId = powerStatsId;
    }

    public Hero(UUID id, String name, String race, UUID powerStatsId) {
	super();
	this.id = id;
	this.name = name;
	this.race = race;
	this.powerStatsId = powerStatsId;
    }


    public UUID getId() {
        return id;
    }
    
    public String getIdString() {
	return this.id.toString();
    }

    public UUID getPowerStatsId() {
        return powerStatsId;
    }
    

    public String getName() {
	return this.name;
    }

    public String getRace() {
	return this.race;
    }
    
    
    
    @Override
    public String toString() {
	return "Hero [name=" + name + "]";
    }
}
