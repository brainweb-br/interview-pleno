package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class PowerStats {

    private long id;
    private int strength;
    private int dexterity;
    private int intelligence;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
