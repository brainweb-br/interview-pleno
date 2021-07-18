package br.com.brainweb.interview.core.features.hero.adapter.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CompareHeroQuery {
    UUID heroId1;

    UUID heroId2;

    int diffStrength;

    int diffAgility;

    int diffDexterity;

    int diffIntelligence;
}
