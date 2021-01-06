package br.com.brainweb.interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoHeroResponse {
    private UUID id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("raca")
    private String breed;

    @JsonProperty("atributos")
    private DtoPowerStatsResponse powerStats;

    @JsonProperty("habilitado")
    private Boolean isEnabled;

    @JsonProperty("data_criação")
    private String creationDate;

    @JsonProperty("data_atualizacao")
    private String updateDate;

    public DtoHeroResponse(Hero hero) {
        this.id = hero.getId();
        this.name = hero.getName();
        this.breed = hero.getBreed();
        this.powerStats = new DtoPowerStatsResponse(hero.getPowerStats());
        this.isEnabled = hero.getIsEnabled();
        this.creationDate = OffsetDateTime.ofInstant(hero.getCreationDate().toInstant(), ZoneId.of("America/Sao_Paulo")).toString();
        this.updateDate = OffsetDateTime.ofInstant(hero.getUpdateDate().toInstant(), ZoneId.of("America/Sao_Paulo")).toString();
    }
}
