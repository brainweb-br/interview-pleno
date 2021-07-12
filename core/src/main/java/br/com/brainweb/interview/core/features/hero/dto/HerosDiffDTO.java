package br.com.brainweb.interview.core.features.hero.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Getter
public class HerosDiffDTO {

    @NotNull
    private UUID heroId1;
    @NotNull
    private UUID heroId2;
    @NotNull
    private int strengthDiff;
    @NotNull
    private int agilityDiff;
    @NotNull
    private int dexterityDiff;
    @NotNull
    private int intelligenceDiff;
}
