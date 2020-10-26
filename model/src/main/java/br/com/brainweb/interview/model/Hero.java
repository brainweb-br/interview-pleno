package br.com.brainweb.interview.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
public class Hero {

    private UUID id;

    @NotNull(message = "Power e obrigatório")
    private UUID power;

    @NotBlank(message = "Nome e obrigatório")
    private String name;

    @Pattern(regexp = "^(HUMAN|ALIEN|DIVINE|CYBORG)$",
            message = "Race inválido")
    private String race;

    private Boolean enabled = Boolean.TRUE;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private OffsetDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private OffsetDateTime updatedAt;

}
