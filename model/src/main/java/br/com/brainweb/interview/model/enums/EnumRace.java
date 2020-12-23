package br.com.brainweb.interview.model.enums;

import lombok.Getter;

@Getter
public enum EnumRace {

    HUMAN("Human", "The human's tanananã"),
    ALIEN("Alien", "The alien's tanananã"),
    DIVINE("Divine", "The divine's tanananã"),
    CYBORG("Cyborg", "The cyborg's tanananã");

    private final String value;
    private final String description;

    EnumRace(String value, String description){
        this.description = description;
        this.value = value;
    }

}
