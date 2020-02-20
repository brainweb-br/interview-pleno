package br.com.brainweb.interview.model.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class HeroDTO {
    private String id;
    private String name;
    private String race;
    private boolean enabled;
    private OffsetDateTime heroCreatedAt;
    private OffsetDateTime heroUpdatedAt;

    private String powerStatsId;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
    private OffsetDateTime powerStatsCreatedAt;
    private OffsetDateTime powerStatsUpdatedAt;
}
