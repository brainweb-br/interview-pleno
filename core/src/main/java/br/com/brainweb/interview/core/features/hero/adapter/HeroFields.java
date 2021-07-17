package br.com.brainweb.interview.core.features.hero.adapter;

public enum HeroFields {
    NAME("name"),
    RACE("race"),
    POWER_STATS_ID("power_stats_id"),
    ENABLED("enabled"),
    CREATED_AT("created_at"),
    UPDATED_AT("updated_at"),
    ID("id");

    private final String bind;

    HeroFields(String bind) {
        this.bind = bind;
    }

    public String getBind() {
        return this.bind;
    }
}
