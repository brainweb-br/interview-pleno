package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Hero{

    private Long id;
    private String name;
    private Race race;
    private PowerStats powerStats;
    private Boolean enable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
