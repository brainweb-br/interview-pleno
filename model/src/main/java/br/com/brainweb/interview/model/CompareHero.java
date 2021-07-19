package br.com.brainweb.interview.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CompareHero {
    UUID heroId1;

    UUID heroId2;

    int strengthDiff;

    int agilityDiff;

    int dexterityDiff;

    int intelligenceDiff;
}
