package br.com.brainweb.interview.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "power_stats", schema = "interview_service")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PowerStats extends AbstractEntity {

    @NotNull
    @Column(name = "agility")
    private Integer agility;

    @NotNull
    @Column(name = "dexterity")
    private Integer dexterity;

    @NotNull
    @Column(name = "intelligence")
    private Integer intelligence;

    @NotNull
    @Column(name = "strength")
    private Integer strength;


}
