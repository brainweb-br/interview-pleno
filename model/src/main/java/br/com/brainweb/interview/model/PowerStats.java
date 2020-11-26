package br.com.brainweb.interview.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "power_stats")
public class PowerStats implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
    private UUID id;

    @NotNull
    @Min(0)
    @Max(10)
    private int strength;

    @NotNull
    @Min(0)
    @Max(10)
    private int agility;

    @NotNull
    @Min(0)
    @Max(10)
    private int dexterity;

    @NotNull
    @Min(0)
    @Max(10)
    private int intelligence;

    @JsonIgnore
	@NotNull
	private LocalDateTime createdAtDateTime;
	
	@JsonIgnore
	@NotNull
	private LocalDateTime updatedAtDateTime;

	@JsonProperty("createdAt")
    public String getCreatedAt() {
    	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return format.format(createdAtDateTime);
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
    	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return format.format(updatedAtDateTime);
    }
    
    public PowerStats(Hero hero) {
		this.strength = hero.getPowerStats().getStrength();
		this.agility = hero.getPowerStats().getAgility();
		this.dexterity = hero.getPowerStats().getDexterity();
		this.intelligence = hero.getPowerStats().getIntelligence();
	}
    
    public Integer getTotalScore(){
        return this.strength + this.agility + this.dexterity + this.intelligence;
    }

	
}
