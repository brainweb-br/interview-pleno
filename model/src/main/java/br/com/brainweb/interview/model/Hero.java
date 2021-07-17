package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Hero {

    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private Race race;

    @NonNull
    private PowerStats powerStats;

    private boolean enabled;

    private LocalDateTime createdDt;

    private LocalDateTime updatedDt;

    public Hero(String name,
                Race race,
                PowerStats powerStats) {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        this.name = name;
        this.race = race;
        this.powerStats = powerStats;
        if(this.createdDt == null){
            this.createdDt = LocalDateTime.now();
        }
        this.updatedDt = LocalDateTime.now();
    }

    public void update(Hero from){
        this.name = from.name;
        this.race = from.race;
        this.enabled = from.enabled;
        this.powerStats = this.powerStats.copy(from.getPowerStats());
        this.updatedDt = from.updatedDt;
    }

    public String getIdString(){
        return this.id.toString();
    }

    public UUID getPowerStatsId() {
        return this.powerStats.getId();
    }

}
