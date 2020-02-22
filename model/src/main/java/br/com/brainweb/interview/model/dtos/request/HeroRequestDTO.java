package br.com.brainweb.interview.model.dtos.request;


import br.com.brainweb.interview.model.entities.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class HeroRequestDTO {
    @NotNull(message = "Name may not be null")
    @NotEmpty(message = "Name may not be empty")
    @NotBlank(message = "Name may not be blank")
    @Length(max = 255)
    private String name;

    @NotNull(message = "Race may not be null")
    private Race race;

    @NotNull(message = "Power stats id may not be null")
    private PowerStats powerStats;

    @NotNull(message = "Enabled may not be null")
    private Boolean enabled;

}
