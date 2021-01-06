package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoPowerStatsResponse {
    private UUID id;

    private Integer strength;

    private Integer agility;

    private Integer dexterity;

    private Integer intelligence;

    private String creationDate;

    private String updateDate;

    public DtoPowerStatsResponse(PowerStats stats) {
        this.id = stats.getId();
        this.strength = stats.getStrength();
        this.agility = stats.getAgility();
        this.dexterity = stats.getDexterity();
        this.intelligence = stats.getIntelligence();
        this.creationDate = OffsetDateTime.ofInstant(stats.getCreationDate().toInstant(), ZoneId.of("America/Sao_Paulo")).toString();
        this.updateDate = OffsetDateTime.ofInstant(stats.getUpdateDate().toInstant(), ZoneId.of("America/Sao_Paulo")).toString();
    }
}
