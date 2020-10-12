package br.com.brainweb.interview.model;

public enum Race {
    HUMAN, ALIEN, DIVINE, CYBORG;

    public static Race getValue(int race) {
        for (Race value:values()) {
            if(value.ordinal() == race){
                return Race.valueOf(value.name());
            }
        }
        throw new IllegalArgumentException();
    }
}
