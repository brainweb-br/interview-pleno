package br.com.brainweb.interview.model.entities;

import br.com.brainweb.interview.model.enums.Race;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hero", schema = "interview_service")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Hero extends EntityBean {


    @NotNull(message = "Name may not be blank")
    @NotEmpty(message = "Name may not be empty")
    @Length(max = 255)
    @Column(name = "name", unique = true)
    private String name;

    @NotNull(message = "Race may not be blank")
    @Enumerated(EnumType.STRING)
    @Column(name = "race")
    private Race race;

    @NotNull(message = "Power stats id may not be blank")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "power_stats_id")
    private PowerStats powerStatsId;

    @NotNull(message = "Enabled may not be blank")
    private Boolean enabled;
}
