package br.com.brainweb.interview.core.features.powerstats.model;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

public class PowerStatsInput {

    @NotNull
    private Short strength;
    @NotNull
    private Short agility;
    @NotNull
    private Short dexterity;
    @NotNull
    private Short intelligence;

}
