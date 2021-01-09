package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "Name nao pode ser nullo")
    @Size(max = 255, message = "Valor do campo(name) maior que o permitido(255)")
    private String name;

    @Column(name = "race", nullable = false)
    @NotNull(message = "race nao pode ser nullo")
    @Size(max = 255, message = "Valor do campo(name) maior que o permitido(255)")
    @Pattern(regexp = "^(HUMAN|ALIEN|DIVINE|CYBORG)$", message = "Race inválido! valores possiveis (HUMAN|ALIEN|DIVINE|CYBORG)")
    private String race;

    @OneToOne
    @JoinColumn(name = "power_stats_id", nullable = false, updatable = false)
    private PowerStats power;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
