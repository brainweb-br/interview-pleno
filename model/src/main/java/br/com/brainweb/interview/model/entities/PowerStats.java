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
public class PowerStats extends EntityBean {


    @NotNull(message = "strength may not be blank")
    @Column(name = "strength")
    private Integer strength;

    @NotNull(message = "agility may not be blank")
    @Column(name = "agility")
    private Integer agility;

    @NotNull(message = "dexterity may not be blank")
    @Column(name = "dexterity")
    private Integer dexterity;

    @NotNull(message = "intelligence may not be blank")
    @Column(name = "intelligence")
    private Integer intelligence;


}
