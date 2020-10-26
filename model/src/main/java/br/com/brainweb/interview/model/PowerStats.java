package br.com.brainweb.interview.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants(asEnum = true)
public class PowerStats {

    private UUID id;

    @NotNull(message = "Strength e obrigatório")
    private Long strength;

    @NotNull(message = "Agility e obrigatório")
    private Long agility;

    @NotNull(message = "Dexterity e obrigatório")
    private Long dexterity;

    @NotNull(message = "Intelligence e obrigatório")
    private Long intelligence;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

}
