package br.com.brainweb.interview.model;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Builder
public class PowerStats {

    @Id
    private UUID id;
    @Column(nullable = false)
    private Short strength;
    @Column(nullable = false)
    private Short agility;
    @Column(nullable = false)
    private Short dexterity;
    @Column(nullable = false)
    private Short intelligence;
    private Timestamp created_at;
    private Timestamp updated_at;

    @PrePersist
    public void addingValues(){
        this.id = UUID.randomUUID();
        this.created_at = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    public void updatingValues(){
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

}
