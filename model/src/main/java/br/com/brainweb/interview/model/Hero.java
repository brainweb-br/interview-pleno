package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@ToString
public class Hero {
    private long id;
    private String name;
    private String race;
    private long powerStatsId;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
