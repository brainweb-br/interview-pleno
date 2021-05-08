package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hero {
    private UUID id;
    private String name;
    private String race; //todo validation
    private Boolean enable;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private PowerStats powerStats;
}
