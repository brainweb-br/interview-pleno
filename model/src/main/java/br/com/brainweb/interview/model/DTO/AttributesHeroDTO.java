package br.com.brainweb.interview.model.DTO;

import br.com.brainweb.interview.model.enums.StatusAttribute;
import lombok.Data;
import java.util.UUID;

@Data
public class AttributesHeroDTO {
    private UUID id;
    private StatusAttribute strength;
    private StatusAttribute agility;
    private StatusAttribute dexterity;
    private StatusAttribute intelligence;
}
