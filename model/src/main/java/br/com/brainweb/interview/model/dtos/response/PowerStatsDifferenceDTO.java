package br.com.brainweb.interview.model.dtos.response;

import lombok.Data;

import java.util.UUID;

@Data
public class PowerStatsDifferenceDTO {

    private UUID firstHeroUUID;

    private UUID secondHeroUUID;

    private Integer agility;

    private Integer dexterity;

    private Integer intelligence;

    private Integer strength;
}
