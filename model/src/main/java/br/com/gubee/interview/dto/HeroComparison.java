package br.com.gubee.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class HeroComparison {
    private UUID hero1Id;
    private UUID hero2Id;
    private int strengthDifference;
    private int agilityDifference;
    private int dexterityDifference;
    private int intelligenceDifference;
}
