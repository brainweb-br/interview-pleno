package br.com.brainweb.interview.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PowerStats {

    private UUID id;
    private Integer strength;
    private Integer agility;
    private Integer dexterity;
    private Integer inteligence;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
