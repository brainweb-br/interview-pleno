package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.dto.HeroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.*;

@Component
public class Facade {

    @Autowired
    HeroService heroService;

    @Autowired
    PowerStatsService powerStatsService;

    @Transactional
    public void save(HeroDTO dto) {
        Hero hero = new Hero();
        PowerStats powerStats = new PowerStats();
        this.mapFromDTO(dto, hero, powerStats);
        String powerStatsId = powerStatsService.save(powerStats);
        hero.setPowerStatsId(UUID.fromString(powerStatsId));
        heroService.save(hero);

    }

    public HeroDTO findById(String id) {
        HeroDTO heroDTO = new HeroDTO();
        Hero hero = heroService.findById(UUID.fromString(id));

        PowerStats powerStats = powerStatsService.findById(hero.getPowerStatsId());

        mapToDTO(heroDTO, hero, powerStats);

        return heroDTO;

    }

    @Transactional
    public void delete(String id) {
        Hero hero = heroService.findById(UUID.fromString(id));
        powerStatsService.delete(hero.getPowerStatsId());
        heroService.delete(UUID.fromString(id));
    }

    public void mapToDTO(HeroDTO heroDTO, Hero hero, PowerStats powerStats) {
        // set from hero
        heroDTO.setId(hero.getId().toString());
        heroDTO.setName(hero.getName());
        heroDTO.setRace(heroDTO.getRace());
        heroDTO.setEnabled(hero.isEnabled());
        heroDTO.setHeroCreatedAt(hero.getCreatedAt());
        heroDTO.setHeroUpdatedAt(hero.getUpdatedAt());
        // set from power stats
        heroDTO.setPowerStatsId(powerStats.getId().toString());
        heroDTO.setStrength(powerStats.getStrength());
        heroDTO.setAgility(powerStats.getAgility());
        heroDTO.setDexterity(powerStats.getDexterity());
        heroDTO.setIntelligence(powerStats.getIntelligence());
        heroDTO.setPowerStatsCreatedAt(powerStats.getCreatedAt());
        heroDTO.setPowerStatsUpdatedAt(powerStats.getUpdatedAt());

    }

    public void mapFromDTO(HeroDTO dto, Hero hero, PowerStats powerStats) {

        // set hero
        hero.setName(dto.getName());
        hero.setRace(dto.getRace());
        hero.setEnabled(dto.isEnabled());
        hero.setCreatedAt(OffsetDateTime.now());
        hero.setUpdatedAt(OffsetDateTime.now());

        // set power stats
        powerStats.setStrength(dto.getStrength());
        powerStats.setDexterity(dto.getDexterity());
        powerStats.setIntelligence(dto.getIntelligence());
        powerStats.setCreatedAt(OffsetDateTime.now());
        powerStats.setUpdatedAt(OffsetDateTime.now());

    }

    public List<HeroDTO> findByName(String heroName) {
        Map<Hero, PowerStats> allMap = new HashMap<>();
        heroService.findByName(heroName)
                .forEach(h -> allMap.put(h, powerStatsService.findById(h.getPowerStatsId())));

        return toDTOS(allMap);
    }

    public List<HeroDTO> getAll() {
        Map<Hero, PowerStats> allMap = new HashMap<>();
        heroService.getAll()
                .forEach(h -> allMap.put(h, powerStatsService.findById(h.getPowerStatsId())));

        return toDTOS(allMap);
    }

    private List<HeroDTO> toDTOS(Map<Hero, PowerStats> allMap) {

        List<HeroDTO> heroDTOS = new ArrayList<>();


        for (Map.Entry<Hero, PowerStats> entry : allMap.entrySet()) {
            HeroDTO heroDTO = new HeroDTO();
            mapToDTO(heroDTO, entry.getKey(), entry.getValue());
            heroDTOS.add(heroDTO);
        }

        return heroDTOS;
    }


}
