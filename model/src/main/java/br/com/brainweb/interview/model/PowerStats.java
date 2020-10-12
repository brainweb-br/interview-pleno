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
public class PowerStats {

    private Long id;
    private Integer strength;
    private Integer agility;
    private Integer dexterity;
    private Integer inteligence;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
