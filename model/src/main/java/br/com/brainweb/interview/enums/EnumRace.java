package br.com.brainweb.interview.enums;

import lombok.Getter;

@Getter
public enum EnumRace {

    HU("Human"),
    AL("Alien"),
    DI("Divine"),
    CY("Cyborg");

    private String value;

    EnumRace(String value){
        this.value = value;
    }

}
