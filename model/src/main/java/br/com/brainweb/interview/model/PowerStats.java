package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "power_stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerStats {

    @Id
    @GeneratedValue( generator = "UUID" )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "strength")
    private Integer strength;

    @Column(name = "agility")
    private Integer agility;

    @Column(name = "dexterity")
    private Integer dexterity;

    @Column(name = "intelligence")
    private Integer intelligence;

    @Column(name = "created_at", columnDefinition = "TIME WITH TIME ZONE")
    private OffsetDateTime creationDate;

    @Column(name = "updated_at", columnDefinition = "TIME WITH TIME ZONE")
    private OffsetDateTime updateDate;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", strength=" + strength +
                ", agility=" + agility +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
