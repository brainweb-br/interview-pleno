package br.com.brainweb.interview.core.features.hero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareHeroResponse {
    private UUID heroId;
    private UUID heroComparedId;
    private Integer strength;
    private Integer agility;
    private Integer dexterity;
    private Integer intelligence;
}
