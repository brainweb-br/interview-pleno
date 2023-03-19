package br.com.brainweb.interview.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
public class PowerStats {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;
    @Column(nullable = false)
    private int strength;
    @Column(nullable = false)
    private int agility;
    @Column(nullable = false)
    private int dexterity;
    @Column(nullable = false)
    private int intelligence;
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;

    public PowerStats() {
    }

    @PrePersist
    public void addingValues() {
        this.created_at = OffsetDateTime.now();
        this.updated_at = OffsetDateTime.now();
    }

    @PreUpdate
    public void updatingValues() {
        this.updated_at = OffsetDateTime.now();
    }
}