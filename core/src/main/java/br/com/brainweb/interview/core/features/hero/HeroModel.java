package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "Hero")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HeroModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int race;
    private Boolean enable;
    private Long powerStatsId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
