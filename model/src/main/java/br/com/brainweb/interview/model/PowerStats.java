package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class PowerStats {

    private UUID id;

    private int  strength;

    private int agility;

    private int dexterity;

    private int intelligence;

    private LocalDateTime createdDt;

    private LocalDateTime updatedDt;

    public PowerStats(){
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

}
