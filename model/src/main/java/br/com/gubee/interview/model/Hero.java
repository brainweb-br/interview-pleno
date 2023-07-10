package br.com.gubee.interview.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class Hero {

    private UUID id;
    private String name;
    private String race;
    private UUID powerStatsId;
    private boolean enabled;
    private Date createdAt;
    private Date updatedAt;

}
