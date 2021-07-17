package br.com.brainweb.interview.core.features.powerstats.adapter;

public enum PowerStatsFields {
    NAME("name"),
    STRENGTH("strength"),
    AGILITY("agility"),
    DEXTERITY("dexterity"),
    INTELLIGENCE("intelligence"),
    CREATED_AT("created_at"),
    UPDATED_AT("updated_at"),
    ID("id");

    private final String bind;

    PowerStatsFields(String bind) {
        this.bind = bind;
    }

    public String getBind() {
        return this.bind;
    }
}
