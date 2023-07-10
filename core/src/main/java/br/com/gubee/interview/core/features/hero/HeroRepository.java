package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.dto.HeroResponse;
import br.com.gubee.interview.model.Hero;

import java.util.UUID;

public interface HeroRepository {

    HeroResponse getById(UUID id);
    HeroResponse getByName(String name);
    UUID getPowerStatsUuidById(UUID uuid);
    int save(HeroResponse hero, UUID uuidPowerStats);
    int deleteById(UUID id);
    int editHero(HeroResponse hero, UUID id);
}
