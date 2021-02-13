package br.com.brainweb.interview.model;

import java.util.UUID;

public class PowerStats {
    
    private UUID id;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
    
    public PowerStats(int strength, int agility, int dexterity, int intelligence) {
	super();
	this.strength = strength;
	this.agility = agility;
	this.dexterity = dexterity;
	this.intelligence = intelligence;
    }
    
    public PowerStats(UUID id, int strength, int agility, int dexterity, int intelligence) {
	super();
	this.id = id;
	this.strength = strength;
	this.agility = agility;
	this.dexterity = dexterity;
	this.intelligence = intelligence;
    }

    public void setId(UUID id){
	if(this.id == null) this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public String toString() {
	return "PowerStats [id=" + id + ", strength=" + strength + ", agility=" + agility + ", dexterity=" + dexterity
		+ ", intelligence=" + intelligence + "]";
    }
    
}
