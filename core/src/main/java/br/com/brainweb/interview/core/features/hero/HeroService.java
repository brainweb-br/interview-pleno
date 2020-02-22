package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.DTO.HeroDTO;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class HeroService {
    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private PowerStatsService powerStatsService;

    public HeroDTO create(HeroDTO heroDTO){
        PowerStats powerStats = this.powerStatsService.create(heroDTO);
        ModelMapper modelMapper = new ModelMapper();
        Hero hero =  modelMapper.map(heroDTO, Hero.class);
        hero.setValues(heroDTO, powerStats.getId());
        hero = heroRepository.save(hero);
        return new HeroDTO(hero, powerStats);
    }

}
