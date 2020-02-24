package br.com.brainweb.interview.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerStatsDifferenceDTO {

    private UUID firstHeroUUID;

    private UUID secondHeroUUID;

    private Integer agility;

    private Integer dexterity;

    private Integer intelligence;

    private Integer strength;
}
