package br.com.brainweb.interview.model.dtos.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "agility", "dexterity", "intelligence", "strength", "created", "updated"})
public class PowerStatsResponseDTO extends ResponseDTO {

    @Builder
    public PowerStatsResponseDTO(UUID id, Integer agility,
                                 Integer dexterity, Integer intelligence,
                                 Integer strength, LocalDateTime created,
                                 LocalDateTime updated) {
        super(id, created, updated);
        this.agility = agility;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.strength = strength;
    }

    private Integer agility;

    private Integer dexterity;

    private Integer intelligence;

    private Integer strength;


}
