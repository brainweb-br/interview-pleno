package br.com.brainweb.interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "hero")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero {

    @Id
    @GeneratedValue( generator = "UUID" )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    @JsonProperty("nome")
    private String name;

    @Column(name = "race")
    @JsonProperty("raca")
    private String breed;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "power_stats_id")
    @JsonProperty("atributos")
    private PowerStats powerStats;

    @Column(name = "enabled")
    @JsonProperty("habilitado")
    private Boolean isEnabled;

    @Column(name = "created_at", columnDefinition = "TIME WITH TIME ZONE")
    @JsonProperty("data_criação")
    private OffsetDateTime creationDate;

    @Column(name = "updated_at", columnDefinition = "TIME WITH TIME ZONE")
    @JsonProperty("data_atualizacao")
    private OffsetDateTime updateDate;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", powerStats=" + powerStats +
                ", isEnabled=" + isEnabled +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
