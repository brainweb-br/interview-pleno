package br.com.brainweb.interview.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CompareHero {
    UUID heroId1;

    UUID heroId2;

    int diffStrength;

    int diffAgility;

    int diffDexterity;

    int diffIntelligence;
}
